package no.lundesgaard.sudokufeud.sudokufeud_android.rest;

import android.util.Base64;
import android.util.Log;
import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.NewProfile;
import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.Profile;
import no.lundesgaard.sudokufeud.sudokufeud_android.util.Constants;
import no.lundesgaard.sudokufeud.sudokufeud_android.util.ResponseUtil;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

public class ProfileServiceClient {

	public String createProfile(NewProfile newProfile) {
		ProfileService profileService = createProfileService();

		final String authString = createAuthString(newProfile.getUserId(), newProfile.getPassword());
		profileService.updateProfile(authString,newProfile);
		return authString;
	}

	public Profile getProfile(String username, String password) {
		return getProfile(createAuthString(username,password));
	}

    public Profile getProfile(String authenticationHeader) {
		ProfileService profileService = createProfileService();

        try {
			return profileService.getProfile(authenticationHeader);
        }
        catch (RetrofitError e) {
			Log.e(Constants.TAG, "geProfile feilet" + ResponseUtil.formatResponse(e.getResponse()), e);
        }
        return null;
    }

	private ProfileService createProfileService() {
		RestAdapter restAdapter = new RestAdapter.Builder()
				.setEndpoint("http://sudokufeud.lundesgaard.no/api")
				.build();

		return restAdapter.create(ProfileService.class);
	}

	private String createAuthString(String username, String password) {
		String toEndode = username + ":" + password;
		try {
			byte[] data = toEndode.getBytes(Constants.ISO_CHARSETNAME);
			String base64 = Base64.encodeToString(data, Base64.DEFAULT);
			return "Basic " + base64;
		} catch (UnsupportedEncodingException e) {
			Log.e(Constants.TAG,"Internal error: " + Constants.ISO_CHARSETNAME + " not found",e);
			// Skal ikke kunne skje
			return null;
		}
	}
}
