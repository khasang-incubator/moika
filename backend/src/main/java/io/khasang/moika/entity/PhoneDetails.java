package io.khasang.moika.entity;

import javax.persistence.*;

/**
 * !!! На удаление ???
 */
@Entity
public class PhoneDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long id;
        private String provider;
        private String technology;
        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "phone_id")
        private Phone phone;

        public PhoneDetails() {
        }
        public PhoneDetails(String provider, String technology) {
            this.provider = provider;
            this.technology = technology;
        }

        public String getProvider() {
            return provider;
        }

        public String getTechnology() {
            return technology;
        }

        public void setTechnology(String technology) {
            this.technology = technology;
        }

        public Phone getPhone() {
            return phone;
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }
    }
