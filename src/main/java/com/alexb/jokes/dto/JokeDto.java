package com.alexb.jokes.dto;

public class JokeDto {
    public JokeDto(String setup, String punchline) {
        this.setup = setup;
        this.punchline = punchline;
    }

    public JokeDto() {
    }

    private String setup;
    private String punchline;

    public String getSetup() {
        return setup;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public String getPunchline() {
        return punchline;
    }

    public void setPunchline(String punchline) {
        this.punchline = punchline;
    }
}
