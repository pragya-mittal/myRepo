package schema;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SchemaCreation {

//    static String currentSchema = "accidental_clicks^Aad_units_rendered^Aad_units_served^Aadserving_cost^Aadunit_publisher_fill_count^Aagency_revenue^Abilled_cpc_clicks^Abl_adgroup_investment^Abl_inmobi_investment^Abrowser_id^Acompleted_views^Acountry_carrier_id^Acpc_impressions^Acpm_clicks^Acpm_impressions^Acreative_charges^Acustom_adunit_id^Adata_enrichment_cost^Ademand_source_type_id^Adevice_model_id^Adevice_type_id^Aevent_time^Afirst_quartiles^Afraud_clicks^Ahandset_device_id^Ainmobi_revenue^Aintegration_direct^Aintegration_family^Aintegration_method^Aintegration_origin^Aintegration_version^Aio_discount^Ais_click_to_rich^Ais_cost^Ais_expandable^Ais_interstitial^Amanufacturer_id^Amatched_conversions^Amedia_plays^Anon_billable_cpc_clicks^Anon_billable_impressions^Aoperating_system_id^Aoperating_system_version_id^Aother_valid_clicks^Apage_requests^Aparticipated_bid^Aparticipated_impressions^Apartner_id^Apassthrough_pay^Aplacement_id^Aplacement_segment_id^Aposition_tier^Aprocess_time_id^Apub_account_inc_id^Apub_business_segment_id^Apub_origin_country_id^Apub_revenue^Apublisher_investment^Arequest_ad_format^Arequest_slot_id^Arequest_time^Aresolved_ad_type^Aresolved_slot_size^Asecond_quartiles^Asegment_id^Aserved_country_id^Aserved_impressions^Aserved_slot_id^Asite_channel_id^Asite_content_id^Asite_inc_id^Asite_platform_id^Asite_slot_id^Asupply_source_type_id^Atemplate_id^Aterminated_clicks^Athird_quartiles^Atotal_ad_requests^Atotal_burn^Atotal_installs^Avalid_ad_requests^Avalid_page_requests^Avat_cost^Avolume_discount^Avqs_count^Avqs_sum^Awithhold_taxes";
//
//    static String verticaSchema = " auto_id                     | int\n" +
//            " event_time                  | timestamp\n" +
//            " request_time                | timestamp\n" +
//            " processed_hour_id           | int\n" +
//            " event_hour_id               | int\n" +
//            " request_hour_id             | int\n" +
//            " pub_account_inc_id          | int\n" +
//            " pub_origin_country_id       | int\n" +
//            " pub_business_segment_id     | int\n" +
//            " site_inc_id                 | int\n" +
//            " site_content_id             | int\n" +
//            " site_platform_id            | int\n" +
//            " site_channel_id             | int\n" +
//            " request_slot_id             | int\n" +
//            " served_slot_id              | int\n" +
//            " site_slot_id                | int\n" +
//            " device_type_id              | int\n" +
//            " operating_system_id         | int\n" +
//            " operating_system_version_id | int\n" +
//            " manufacturer_id             | int\n" +
//            " browser_id                  | int\n" +
//            " served_country_id           | int\n" +
//            " country_carrier_id          | int\n" +
//            " demand_source_type_id       | int\n" +
//            " is_expandable               | int\n" +
//            " is_interstitial             | boolean\n" +
//            " is_click_to_rich            | int\n" +
//            " integration_method          | varchar(15000)\n" +
//            " integration_family          | varchar(15000)\n" +
//            " integration_version         | varchar(15000)\n" +
//            " integration_origin          | varchar(15000)\n" +
//            " integration_direct          | varchar(15000)\n" +
//            " supply_source_type_id       | int\n" +
//            " segment_id                  | int\n" +
//            " page_requests               | int\n" +
//            " valid_page_requests         | int\n" +
//            " total_ad_requests           | int\n" +
//            " valid_ad_requests           | int\n" +
//            " cpc_impressions             | int\n" +
//            " cpm_impressions             | int\n" +
//            " billed_cpc_clicks           | int\n" +
//            " non_billable_cpc_clicks     | int\n" +
//            " non_billable_impressions    | int\n" +
//            " predicted_clicks            | int\n" +
//            " participated_bid            | float\n" +
//            " participated_impressions    | int\n" +
//            " total_burn                  | float\n" +
//            " pub_revenue                 | float\n" +
//            " inmobi_revenue              | float\n" +
//            " terminated_clicks           | int\n" +
//            " fraud_clicks                | int\n" +
//            " accidental_clicks           | int\n" +
//            " matched_conversions         | int\n" +
//            " served_impressions          | int\n" +
//            " cpm_clicks                  | int\n" +
//            " template_id                 | int\n" +
//            " custom_ad_unit_id           | int\n" +
//            " ad_units_served             | int\n" +
//            " ad_units_rendered           | int\n" +
//            " position_tier               | int\n" +
//            " adunit_publisher_fill_count | int\n" +
//            " is_cost                     | float\n" +
//            " partner_id                  | varchar(100)\n" +
//            " resolved_ad_type            | varchar(30)\n" +
//            " other_valid_clicks          | int\n" +
//            " adserving_cost              | float\n" +
//            " resolved_slot_size          | varchar(50)\n" +
//            " vqs_count                   | int\n" +
//            " first_quartiles             | int\n" +
//            " second_quartiles            | int\n" +
//            " third_quartiles             | int\n" +
//            " completed_views             | int\n" +
//            " media_plays                 | int\n" +
//            " vqs_sum                     | float\n" +
//            " placement_id                | int";


    public Properties getProperties() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("/Users/pragya.mittal/workspace/JavaBasic/src/main/resources/sample/schema.properties");
            // load a properties file
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  prop;
    }

    public void writeProperties(String propName, String propValue) {
        Properties prop = new Properties();
        OutputStream output = null;
        try {

            output = new FileOutputStream("/Users/pragya.mittal/workspace/JavaBasic/src/main/resources/sample/output/" + propName + ".properties");

            // set the properties value
            prop.setProperty(propName, propValue);

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

//    public void writeSchema(String[] args) {
//
//        BufferedWriter bw = null;
//        FileWriter fw = null;
//
//        try {
//
//            String data = " This is new content";
//
//            File file = new File(FILENAME);
//
//            // if file doesnt exists, then create it
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//
//            // true = append file
//            fw = new FileWriter(file.getAbsoluteFile(), true);
//            bw = new BufferedWriter(fw);
//
//            bw.write(data);
//
//            System.out.println("Done");
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//
//        }
//
//    }


    public String[] splitControlA(String inputStr, String delim) {
        String[] separator = inputStr.split(delim);
        return separator;
    }

    public Map<String ,String> createOldMap(String[] input, String delim) {
        Map<String, String> inputMap = new HashMap<String, String>();
        for (String string : input) {
            String[] splitInput = string.split(delim);
            inputMap.put(splitInput[0].trim(), splitInput[1].trim());

        }
        return inputMap;
    }

    public String createNewMap(Map<String , String > oldMap, String[] oldSchema) {
        String command = "";
        for (String string : oldSchema) {
            if(oldMap.containsKey(string)) {
                command = command + "[" + string + "] " + oldMap.get(string) + " NOT NULL, \n";
            } else {
                command = command + "[" + string + "] varchar(50) NOT NULL, \n";
            }
        }
        String val = command.replaceAll("varchar\\(15000\\)","varchar\\(8000\\)").replaceAll("boolean","bit").replaceAll("timestamp","datetime");
        return val;
    }

    public static void main(String[] args) throws IOException {

        String[] listFacts = {"hourSupply", "houragg1Supply", "daySupply", "dayagg1Supply", "hourNetwork", "houragg1Network", "houragg2Network", "hourDemand", "houragg1Demand", "houragg2Demand",  "dayNetworkhadoopSchema", "dayNetworkverticaSchema", "dayagg1NetworkhadoopSchema", "dayagg1NetworkverticaSchema", "dayagg2NetworkhadoopSchema", "dayagg2NetworkverticaSchema", "dayDemandhadoopSchema", "dayDemandverticaSchema", "dayagg1DemandhadoopSchema", "dayagg1DemandverticaSchema", "dayagg2DemandhadoopSchema", "dayagg2DemandverticaSchema", };
        //select column_name, data_type, is_nullable from columns where table_schema='unified_global_dw' and table_name='hour_agg1_supply_fact';
//        SchemaCreation schemaCreation = new SchemaCreation();
//        String old[] = schemaCreation.splitControlA(currentSchema, "\\^A");
//        String pairs[] = schemaCreation.splitControlA(verticaSchema, "\n");
//        Map<String , String> schemaMap = schemaCreation.createOldMap(pairs, "\\|");
//        String command = schemaCreation.createNewMap(schemaMap, old);
//        System.out.println(command);

        System.out.println("Hi");

//        SchemaCreation schemaCreation1 = new SchemaCreation();
//        Properties prop = schemaCreation1.getProperties();
//        String old1[] = schemaCreation1.splitControlA(prop.getProperty("hourSupplyhadoopSchema"), "\\^A");
//        String pairs1[] = schemaCreation1.splitControlA(prop.getProperty("hourSupplyverticaSchema"), ",");
//        Map<String , String> schemaMap1 = schemaCreation1.createOldMap(pairs1, "\\|");
//        String command1 = schemaCreation1.createNewMap(schemaMap1, old1);
//        System.out.println(command1);
//        schemaCreation1.writeProperties("hourSupply", command1);

        for(String str : listFacts) {
            SchemaCreation schemaCreation = new SchemaCreation();
            Properties prop = schemaCreation.getProperties();
            String old[] = schemaCreation.splitControlA(prop.getProperty(str + "hadoopSchema"), "\\^A");
            String pairs[] = schemaCreation.splitControlA(prop.getProperty(str + "verticaSchema"), ",");
            Map<String , String> schemaMap = schemaCreation.createOldMap(pairs, "\\|");
            String command = schemaCreation.createNewMap(schemaMap, old);
            System.out.println("======================================");
            System.out.println(str);
            System.out.println(command);
            schemaCreation.writeProperties(str, command);
        }

    }
}
