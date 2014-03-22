package no.lundesgaard.sudokufeud.sudokufeud_android.rest;

import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.Profile;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class ProfileServiceClient {

    public Profile geProfile(/*String authenticationHeader*/) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://sudokufeud.lundesgaard.no/api")
                .build();

        ProfileService profileService = restAdapter.create(ProfileService.class);

        try {
            Profile profile = profileService.getProfile("Basic YW5kZXJzOmFuZGVyczEyMw==");
            System.out.println(profile);
            return profile;
        }
        catch (RetrofitError e) {
            e.printStackTrace();
        }
        return null;
    }
}
