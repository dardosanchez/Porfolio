package com.portfolio_backend.services;

import com.portfolio_backend.models.Profile;
import com.portfolio_backend.models.dto.ProfileDTO;

import java.util.List;

public interface IProfileService {

    public Profile guardarPerfil (ProfileDTO profileDTO) throws Exception;

    public Profile getProfile (Long id);

    public List<Profile> getProfiles ();

    public Profile editProfile (Long id, ProfileDTO profileDTO) throws Exception;

    public String deleteProfile (Long id);

}
