package com.gvnc.search;

/**
 * Created by EXT01D3678 on 23.9.2016.
 */

import com.gvnc.search.model.Airport;
import com.gvnc.search.model.Carrier;
import com.gvnc.search.model.Flight;
import com.gvnc.search.model.Plane;
import com.gvnc.search.repository.FlightRepository;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.gvnc.search")
public class SampleDataCreator {

    // set this true to use embedded elastic search server
    boolean useEmbeddedElasticSearchServer = false;

    String airportsCsvFile = "airports.csv";
    private HashMap<String,Airport> airports = new HashMap<String,Airport>();

    String carriersCsvFile = "carriers.csv";
    private HashMap<String,Carrier> carriers = new HashMap<String,Carrier>();

    String planesCsvFile = "plane-data.csv";
    private HashMap<String,Plane> planes = new HashMap<String,Plane>();

    String flightsCsvFile = "2008.csv";

    @Autowired
    private FlightRepository repository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Bean
    public Client getNodeClient() {
        try {
            if(useEmbeddedElasticSearchServer == true) {
                return (NodeClient) nodeBuilder()
                            .settings(Settings.builder()
                                .put("path.home", "./"))
                        .clusterName("elasticsearch").local(true).node().client();
            } else {
                // cluster.name is elasticsearch by default
                Settings settings = Settings.settingsBuilder()
                        .put("cluster.name", "elasticsearch")
                        .put("client.transport.sniff", true)
                        .build();

                TransportClient client = TransportClient.builder().settings(settings).build()
                        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
                return client;
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void processData(String dataFilesPath, int bulkInsertSize){
        loadAirports(dataFilesPath + airportsCsvFile);
        System.out.println("airports loadded");
        loadCarriers(dataFilesPath + carriersCsvFile);
        System.out.println("carriers loadded");
        loadPlanes(dataFilesPath + planesCsvFile);
        System.out.println("plane data loaded");
        processFlights(dataFilesPath + flightsCsvFile, bulkInsertSize);
        System.out.println("flights saved");
    }

    private void processFlights(String fileName, int bulkInsertSize){
        System.out.println("starting to process flight records");
        System.out.println("bulk insert size : " + bulkInsertSize);

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int counter = 0;
        List flightList = new ArrayList();
        try {
            Long flightId = new Long(1);
            br = new BufferedReader(new FileReader(fileName));
            String headers = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.split(cvsSplitBy);
                Flight flight = new Flight();
                flight.setId(Long.toString(flightId));
                flight.setYear(getIntValue(lineSplit[0]));
                flight.setMonth(getIntValue(lineSplit[1]));
                flight.setDayofMonth(getIntValue(lineSplit[2]));
                flight.setDayOfWeek(getIntValue(lineSplit[3]));
                flight.setDepTime(lineSplit[4]);
                flight.setCrsDepTime(lineSplit[5]);
                flight.setArrTime(lineSplit[6]);
                flight.setCrsArrTime(lineSplit[7]);
                flight.setUniqueCarrier(carriers.get(lineSplit[8]));
                flight.setFlightNum(lineSplit[9]);
                flight.setPlane(planes.get(lineSplit[10]));
                flight.setActualElapsedTime(lineSplit[11]);
                flight.setCrsElapsedTime(lineSplit[12]);
                flight.setAirTime(lineSplit[13]);
                flight.setArrDelay(lineSplit[14]);
                flight.setDepDelay(lineSplit[15]);
                flight.setOrigin(airports.get(lineSplit[16]));
                flight.setDest(airports.get(lineSplit[17]));
                flight.setDistance(getIntValue(lineSplit[18]));
                flight.setTaxiIn(lineSplit[19]);
                flight.setTaxiOut(lineSplit[20]);
                flight.setCancelled(lineSplit[21]);
                flight.setCancellationCode(lineSplit[22]);
                flight.setDiverted(lineSplit[23]);
                flight.setCarrierDelay(lineSplit[24]);
                flight.setWeatherDelay(lineSplit[25]);
                flight.setNasDelay(lineSplit[26]);
                flight.setSecurityDelay(lineSplit[27]);
                flight.setLateAircraftDelay(lineSplit[28]);
                flightId ++;

                flightList.add(flight);
                counter ++;
                if(counter == bulkInsertSize) {
                    System.out.println("saving "+ bulkInsertSize + " records");
                    repository.save(flightList);
                    counter = 0;
                    flightList = new ArrayList();
                }
            }
            if(counter > 0){
                System.out.println("saving " + counter + " records");
                repository.save(flightList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("completed processing flight records");
    }

    private void loadAirports(String fileName){
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(fileName));
            String headers = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.replaceAll("\"","").split(cvsSplitBy);
                Airport airport = new Airport();
                airport.setIata(lineSplit[0]);
                airport.setAirport(lineSplit[1]);
                airport.setCity(lineSplit[2]);
                airport.setState(lineSplit[3]);
                airport.setCountry(lineSplit[4]);
                airport.setLatValue(lineSplit[5]);
                airport.setLongValue(lineSplit[6]);
                airports.put(airport.getIata(),airport);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadCarriers(String fileName){

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(fileName));
            String headers = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.replaceAll("\"","").split(cvsSplitBy);
                Carrier carrier = new Carrier();
                carrier.setCode(lineSplit[0]);
                carrier.setDescription(lineSplit[1]);
                carriers.put(carrier.getCode(), carrier);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadPlanes(String fileName){

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(fileName));
            String headers = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.replaceAll("\"","").split(cvsSplitBy);
                Plane plane = new Plane();
                plane.setTailnum(lineSplit[0]);
                if(lineSplit.length > 1) {
                    plane.setType(lineSplit[1]);
                    plane.setManufacturer(lineSplit[2]);
                    plane.setIssueDate(lineSplit[3]);
                    plane.setModel(lineSplit[4]);
                    plane.setStatus(lineSplit[5]);
                    plane.setAircraftType(lineSplit[6]);
                    plane.setEngineType(lineSplit[7]);
                    plane.setYear(lineSplit[8]);
                }
                planes.put(plane.getTailnum(), plane);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Integer getIntValue(String intStr){
        try {
            return Integer.parseInt(intStr);
        } catch (Exception e) {
            return 0;
        }
    }

}
