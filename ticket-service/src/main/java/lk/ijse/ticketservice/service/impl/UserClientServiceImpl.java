package lk.ijse.ticketservice.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.ticketservice.service.UserClientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserClientServiceImpl implements UserClientService {
    private static final Logger logger = LoggerFactory.getLogger(UserClientServiceImpl.class);
    private final RestTemplate restTemplate;
    @Override
    public boolean isExitsUser(String id) {
        try {
            String url = "http://user-service/api/v1/users/userExists/" + id;
            Boolean userExists = restTemplate.getForObject(url, Boolean.class);
            logger.info("User Exists: {}", userExists);
            return userExists != null && userExists;
        } catch (Exception e) {
            logger.error("Error checking if user exists", e);
        }
        return false;
    }
}
