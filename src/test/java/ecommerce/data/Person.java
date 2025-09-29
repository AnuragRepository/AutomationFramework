package ecommerce.data;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;


    public class Person {
        @JsonProperty("emailID")
        private String emailID;

        @JsonProperty("password")
        private String password;

        @JsonProperty("arrItems1")
        private List<String> arrItems1;

        public String getEmailID() {
            return emailID;
        }

        public void setEmailID(String emailID) {
            this.emailID = emailID;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public List<String> getArrItems1() {
            return arrItems1;
        }

        public void setArrItems1(List<String> arrItems1) {
            this.arrItems1 = arrItems1;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(emailID, person.emailID) && Objects.equals(password, person.password) && Objects.equals(arrItems1, person.arrItems1);
        }

        @Override
        public int hashCode() {
            return Objects.hash(emailID, password, arrItems1);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "emailID='" + emailID + '\'' +
                    ", password='" + password + '\'' +
                    ", arrItems1=" + arrItems1 +
                    '}';
        }
    }

