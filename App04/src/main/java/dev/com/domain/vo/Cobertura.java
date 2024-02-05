package dev.com.domain.vo;

public record Cobertura(IP address, String name, int cidr) {

    public Cobertura {
        if (cidr < 1 || cidr > 32) {
            throw new IllegalArgumentException("Invalid CIDR value");
        }
    }

    @Override
    public String toString() {
        return "Network{" +
                "address=" + address +
                ", name='" + name + '\'' +
                ", cidr=" + cidr +
                '}';
    }

    public IP getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public int getCidr() {
        return cidr;
    }
}
