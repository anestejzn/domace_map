package rs.ac.uns.ftn.siit.sw442019.graduate.dto.response;

import rs.ac.uns.ftn.siit.sw442019.graduate.model.Household;

public class HouseholdPageResponse extends HouseholdResponse {
    private int pageSize;
    private int pageNumber;

    public HouseholdPageResponse(Household household, int pageSize, int pageNumber) {
        super(household);
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }
}
