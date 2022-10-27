package be.vdab.prularia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MagazijnplaatsIdEnStatus(@JsonProperty("magazijnplaatsid") long magazijnplaatsId, boolean status) {}