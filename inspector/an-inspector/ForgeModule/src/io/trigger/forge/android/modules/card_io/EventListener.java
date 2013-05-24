package io.trigger.forge.android.modules.card_io;
 
import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;
import io.trigger.forge.android.core.ForgeApp;
import io.trigger.forge.android.core.ForgeEventListener;
import android.content.Intent;
 
import com.google.gson.JsonObject;
 
public class EventListener extends ForgeEventListener {
    @Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
 
	    if (requestCode == 4278) {
	        String resultDisplayStr;
	        if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
	            CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);
 
	            // Never log a raw card number. Avoid displaying it, but if necessary use getFormattedCardNumber()
	            resultDisplayStr = "Card Number: " + scanResult.getRedactedCardNumber() + "\n";
 
	            // Do something with the raw number, e.g.:
	            // myService.setCardNumber( scanResult.cardNumber );
 
	            if (scanResult.isExpiryValid()) {
	                resultDisplayStr += "Expiration Date: " + scanResult.expiryMonth + "/" + scanResult.expiryYear + "\n"; 
	            }
 
	            if (scanResult.cvv != null) { 
	                // Never log or display a CVV
	                resultDisplayStr += "CVV has " + scanResult.cvv.length() + " digits.\n";
	            }
 
	            if (scanResult.zip != null) {
	                resultDisplayStr += "Zip: " + scanResult.zip + "\n";
	            }
	        }
	        else {
	            resultDisplayStr = "Scan was canceled.";
	        }
	        JsonObject result = new JsonObject();
	        result.addProperty("data", resultDisplayStr);
	        
	        ForgeApp.event("card_io.scanned", result);
	    }
	    // else handle other activity results 
	}
}