package schema;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SchemaCreationNew {

    public Properties getProperties() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("/Users/pragya.mittal/workspace/JavaBasic/src/main/resources/dstschema.properties");
            // load properties file
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

            output = new FileOutputStream("/Users/pragya.mittal/workspace/JavaBasic/src/main/resources/output/" + propName + ".properties");

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


    public String[] splitOnDelim(String inputStr, String delim) {
        String[] separator = inputStr.split(delim);
        return separator;
    }

    public Map<String ,String> createOldMap(String[] input, String delim) {
        Map<String, String> inputMap = new HashMap<String, String>();
        for (String string : input) {
            String[] splitInput = string.split(delim);
            inputMap.put(splitInput[0].trim(), splitInput[1].trim() + "," + splitInput[2].trim());

        }
        return inputMap;
    }

    public String createNewMap(Map<String , String > oldMap, String[] oldSchema) {
        String command = "";
        for (String string : oldSchema) {
            if(oldMap.containsKey(string)) {
                String values[] = oldMap.get(string).split(",");
                if(values[1].trim().equals("t")) {
                    command = command + "\t\t\t\t[" + string + "] " + values[0].trim() + ", \n";
                } else if(values[1].trim().equals("f")) {
                    command = command + "\t\t\t\t[" + string + "] " + values[0].trim() + " NOT NULL, \n";
                }
            } else {
                command = command + "\t\t\t\t[" + string + "] varchar(50) NOT NULL, \n";
            }
        }
        String val = command.replaceAll("varchar\\(15000\\)","varchar\\(8000\\)").replaceAll("boolean","bit").replaceAll("timestamp","datetime").replaceAll(" int,", " bigint,");
        return val;
    }

    public static void main(String[] args) throws IOException {

//        String[] listFacts = {"hourly_supply", "hourly_agg1_supply", "daily_supply", "daily_agg1_supply", "hourNetwork", "houragg1Network", "houragg2Network", "hourDemand", "houragg1Demand", "houragg2Demand",  "dayNetworkhadoopSchema", "dayNetworkverticaSchema", "dayagg1NetworkhadoopSchema", "dayagg1NetworkverticaSchema", "dayagg2NetworkhadoopSchema", "dayagg2NetworkverticaSchema", "dayDemandhadoopSchema", "dayDemandverticaSchema", "dayagg1DemandhadoopSchema", "dayagg1DemandverticaSchema", "dayagg2DemandhadoopSchema", "dayagg2DemandverticaSchema"};

        String[] listFacts = {"hourly_supply", "hourly_agg1_supply", "daily_supply", "daily_agg1_supply", "hourly_network_perf", "hourly_agg1_network_perf", "hourly_agg2_network_perf", "hourly_demand_perf", "hourly_agg1_demand_perf", "hourly_agg2_demand_perf",  "daily_network_perf", "daily_agg1_network_perf", "daily_agg2_network_perf", "daily_demand_perf", "daily_agg1_demand_perf", "daily_agg2_demand_perf", "hourly_network_ix", "hourly_agg1_network_ix", "hourly_agg2_network_ix", "hourly_demand_ix", "hourly_agg1_demand_ix", "hourly_agg2_demand_ix",  "daily_network_ix", "daily_agg1_network_ix", "daily_agg2_network_ix", "daily_demand_ix", "daily_agg1_demand_ix", "daily_agg2_demand_ix", "hourly_network_ifc", "hourly_agg1_network_ifc", "hourly_agg2_network_ifc", "hourly_demand_ifc", "hourly_agg1_demand_ifc", "hourly_agg2_demand_ifc",  "daily_network_ifc", "daily_agg1_network_ifc", "daily_agg2_network_ifc", "daily_demand_ifc", "daily_agg1_demand_ifc", "daily_agg2_demand_ifc", "hourly_network_brand", "hourly_agg1_network_brand", "hourly_agg2_network_brand", "hourly_demand_brand", "hourly_agg1_demand_brand", "hourly_agg2_demand_brand",  "daily_network_brand", "daily_agg1_network_brand", "daily_agg2_network_brand", "daily_demand_brand", "daily_agg1_demand_brand", "daily_agg2_demand_brand", "hourly_network_rtbd", "hourly_agg1_network_rtbd", "hourly_agg2_network_rtbd", "hourly_demand_rtbd", "hourly_agg1_demand_rtbd", "hourly_agg2_demand_rtbd",  "daily_network_rtbd", "daily_agg1_network_rtbd", "daily_agg2_network_rtbd", "daily_demand_rtbd", "daily_agg1_demand_rtbd", "daily_agg2_demand_rtbd", "hourly_network_asm", "hourly_agg1_network_asm", "hourly_agg2_network_asm", "hourly_demand_asm", "hourly_agg1_demand_asm", "hourly_agg2_demand_asm",  "daily_network_asm", "daily_agg1_network_asm", "daily_agg2_network_asm", "daily_demand_asm", "daily_agg1_demand_asm", "daily_agg2_demand_asm"};

        System.out.println("Hi");

//        for(String str : listFacts) {
//            SchemaCreationNew schemaCreation = new SchemaCreationNew();
//            Properties prop = schemaCreation.getProperties();
////            String old[] = schemaCreation.splitControlA(prop.getProperty(str + "hadoopSchema"), "\\^A");
//            String old[] = schemaCreation.splitOnDelim(prop.getProperty(str + "hadoopSchema"), ",");
//            String pairs[] = schemaCreation.splitOnDelim(prop.getProperty(str + "verticaSchema"), ",");
//            Map<String , String> schemaMap = schemaCreation.createOldMap(pairs, "\\|");
//            String command = schemaCreation.createNewMap(schemaMap, old);
//            System.out.println("======================================");
//            System.out.println(str);
//            System.out.println(command);
//            schemaCreation.writeProperties(str, command);
//        }
        
        /*
        
        IF (@tableName = 'hourly_demand_brand')
        BEGIN
            SET @tableFields = ''
        END

         */

        for(String str : listFacts) {
            SchemaCreationNew schemaCreation = new SchemaCreationNew();
            Properties prop = schemaCreation.getProperties();
//            String old[] = schemaCreation.splitControlA(prop.getProperty(str + "hadoopSchema"), "\\^A");
            String old[] = schemaCreation.splitOnDelim(prop.getProperty(str + "hadoopSchema"), ",");
            String pairs[] = schemaCreation.splitOnDelim(prop.getProperty(str + "verticaSchema"), ",");
            Map<String , String> schemaMap = schemaCreation.createOldMap(pairs, "\\|");
            String command = schemaCreation.createNewMap(schemaMap, old);
            System.out.println("\n");
            System.out.println("\tIF (@tableName = '" + str + "')");
            System.out.println("\t\tBEGIN");
            System.out.println("\t\t\tSET @tableFields = '");
            System.out.println("\t\t\t"+command +"'");
            System.out.println("\t\tEND");
            System.out.println("\n");
            schemaCreation.writeProperties(str, command);
        }

    }
}
