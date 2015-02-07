package com.safien.code2015.disasterapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by salman on 2014-11-15.
 */
@Document(collection = "users")
public class UserEntity {

    @Id
    private String id;
    private String username;
    private String password;
    @Field("pitch")
    private UserPitch userPitch;
    @Field("yaw")
    private UserYaw userYaw;
    @Field("roll")
    private UserRoll userRoll;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserPitch getUserPitch() {
        return userPitch;
    }

    public void setUserPitch(UserPitch userPitch) {
        this.userPitch = userPitch;
    }

    public UserYaw getUserYaw() {
        return userYaw;
    }

    public void setUserYaw(UserYaw userYaw) {
        this.userYaw = userYaw;
    }

    public UserRoll getUserRoll() {
        return userRoll;
    }

    public void setUserRoll(UserRoll userRoll) {
        this.userRoll = userRoll;
    }

    @Document
    public class UserPitch {

        private double base;
        private double max;
        private double min;

        public double getBase() {
            return base;
        }

        public void setBase(double base) {
            this.base = base;
        }

        public double getMax() {
            return max;
        }

        public void setMax(double max) {
            this.max = max;
        }

        public double getMin() {
            return min;
        }

        public void setMin(double min) {
            this.min = min;
        }
    }
    @Document
    public class UserYaw {

        private double base;
        private double max;
        private double min;

        public double getBase() {
            return base;
        }

        public void setBase(double base) {
            this.base = base;
        }

        public double getMax() {
            return max;
        }

        public void setMax(double max) {
            this.max = max;
        }

        public double getMin() {
            return min;
        }

        public void setMin(double min) {
            this.min = min;
        }
    }

    @Document
    public class UserRoll {

        private double base;
        private double max;
        private double min;

        public double getBase() {
            return base;
        }

        public void setBase(double base) {
            this.base = base;
        }

        public double getMax() {
            return max;
        }

        public void setMax(double max) {
            this.max = max;
        }

        public double getMin() {
            return min;
        }

        public void setMin(double min) {
            this.min = min;
        }
    }


}
