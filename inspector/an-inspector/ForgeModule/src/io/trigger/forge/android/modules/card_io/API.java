package io.trigger.forge.android.modules.card_io;

import io.card.payment.CardIOActivity;
import io.trigger.forge.android.core.ForgeApp;
import io.trigger.forge.android.core.ForgeTask;
import android.content.Intent;

public class API {
	
	public static void scan(final ForgeTask task) {
	    Intent scanIntent = new Intent(ForgeApp.getActivity(), CardIOActivity.class);

	    // required for authentication with card.io
	    scanIntent.putExtra(CardIOActivity.EXTRA_APP_TOKEN, "94d31fc4ec4c4931b4c2697f7579468e");

	    // customize these values to suit your needs.
	    scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true); // default: true
	    scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false); // default: false
	    scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_ZIP, false); // default: false

	    // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
	    ForgeApp.getActivity().startActivityForResult(scanIntent, 4278);
	}
}
