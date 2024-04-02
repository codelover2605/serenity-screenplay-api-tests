package models;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

@Builder
@Data
public class RatesResponse {
    private String result;
    private String provider;
    private String documentation;
    private String terms_of_use;
    private String time_last_update_unix;
    private String time_last_update_utc;
    private String time_next_update_unix;
    private String time_next_update_utc;
    private int time_eol_unix;
    private String base_code;
    private HashMap<String, Double> rates;
}
