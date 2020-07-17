package com.stv.plugins.chip;

import android.device.IccManager;
import android.widget.TextView;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChipPlugin extends CordovaPlugin {

    private IccManager iccManager;
    private byte[] apdu_utf = {
            0x00, (byte) 0xA4, 0x04, 0x00, 0x0E, 0x31, 0x50, 0x41, 0x59, 0x2E, 0x53,
            0x59, 0x53, 0x2E, 0x44, 0x44, 0x46, 0x30, 0x31, 0x00
    };

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

      if(action.equals("openReader")) {
            // instance
            iccManager = new IccManager();
            // open reader
            if(iccManager.open((byte) 0, (byte) 1, (byte) 1) == 0) {
              callbackContext.success("Reader Open");
            } else {
              callbackContext.success("Unable to open reader");
            }
            return true;

        } else if(action.equals("detectCard")) {
            // detecting
            int detect = iccManager.detect();
            if(detect == 0) {
              byte[] atr = new byte[64];
              int act = iccManager.activate(atr);
              // activating read
              if(act >= 0) {
                this.grabATR(atr, act, callbackContext);
              } else {
                callbackContext.error("Unable to detect card");
              }
            } else {
              callbackContext.error("Unable to detect card");
            }
            return true;

        } else if(action.equals("transmitApdu")) {
            // transmiting
            byte[] response = new byte[256];
            byte[] statusCode = new byte[2];
            int transmit = iccManager.apduTransmit(apdu_utf, apdu_utf.length, response, statusCode);
            if(transmit >= 0) {
              int length = apdu_utf.length;
              this.transmitApdu(transmit, response, statusCode, callbackContext);
            }
            return true;
        }
        return false;
    }

    private void grabATR(byte[] atr, int act, CallbackContext callbackContext) {
      callbackContext.success("ATR: " + ChipPlugin.bytesToHexString(atr, 0, act));
    }

    private void transmitApdu(int transmit, byte[] rsp, byte[] sc, CallbackContext callbackContext) {
      String response = ChipPlugin.bytesToHexString(rsp, 0, transmit);
      String status = ChipPlugin.bytesToHexString(sc, 0, 2);
      callbackContext.success(response + " - " + status);
    }

    private static String bytesToHexString(byte[] src, int offset, int length) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = offset; i < length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
