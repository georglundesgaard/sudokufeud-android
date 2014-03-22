package no.lundesgaard.sudokufeud.sudokufeud_android.rest;

import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.Profile;
import retrofit.http.GET;
import retrofit.http.Header;

public interface ProfileService {

    @GET("profile")
    Profile getProfile(@Header("Authorization") String authorization);
}
