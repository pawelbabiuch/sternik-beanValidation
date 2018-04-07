package pl.sternik.pb.bv.grupy;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

//@GroupSequence({Address.OnlyStreet.class,Address.class})
//@GroupSequence({Address.class,Address.OnlyStreet.class})
public class Address {
    @NotNull(groups=OnlyStreet.class, message="OnlyStreet NN")
    @Size(max = 50)
    private String street;

    @NotNull(groups=OnlyZip.class, message="OnlyZip NN")
    private String zipcode;

    @NotNull
    @Size(max = 30)
    private String city;

    public interface OnlyStreet {}

    public interface OnlyZip {}

    @GroupSequence({Default.class, OnlyZip.class,OnlyStreet.class})
    public interface Complete {}

    @GroupSequence({OnlyStreet.class,OnlyZip.class})
    public interface OnlyGroups {}

    public String getStreet1() {
        return street;
    }

    public void setStreet1(String street1) {
        this.street = street1;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}