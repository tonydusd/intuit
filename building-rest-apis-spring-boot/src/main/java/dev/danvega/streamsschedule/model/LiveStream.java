package dev.danvega.streamsschedule.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class LiveStream {
        private String firstName;
        private String lastName;
        private String dob;
        private String email;
        private String phone;

        public LiveStream(String firstName, String lastName, String dob, String email, String phone){
                this.firstName = firstName;
                this.lastName = lastName;
                this.dob = dob;
                this.email = email;
                this.phone = phone;
        }
};
