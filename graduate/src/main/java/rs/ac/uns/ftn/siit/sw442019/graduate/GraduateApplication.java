package rs.ac.uns.ftn.siit.sw442019.graduate;

import com.graphhopper.GraphHopper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static rs.ac.uns.ftn.siit.sw442019.graduate.util.GraphHopperUtil.createGraphHopperInstance;

@SpringBootApplication
public class GraduateApplication {

	public static GraphHopper hopper = createGraphHopperInstance("src/main/resources/core/files/serbia-latest.osm.pbf");
	public static void main(String[] args) {
		SpringApplication.run(GraduateApplication.class, args);
	}

}
