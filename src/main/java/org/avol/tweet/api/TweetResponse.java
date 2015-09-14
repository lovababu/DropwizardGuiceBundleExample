package org.avol.tweet.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Durga on 9/11/2015.
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TweetResponse {

    @JsonProperty
    private String message;

    @JsonProperty
    private int statusCode;

    @JsonProperty
    private List<Tweet> tweets;

}
