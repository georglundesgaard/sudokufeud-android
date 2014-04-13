package no.lundesgaard.sudokufeud.sudokufeud_android.rest;

import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.NewProfile;
import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.Profile;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.PUT;

public interface ProfileService {

    @GET("/profile")
    Profile getProfile(@Header("Authorization") String authorization);

	@PUT("/profile")
	Profile updateProfile(@Header("Authorization") String authorization, @Body NewProfile profile);
}
