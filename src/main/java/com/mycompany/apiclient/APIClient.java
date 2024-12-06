package com.mycompany.apiclient;


import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;


 public class APIClient {
     //Atributos generales
     private static String token = "";
     private static String base_url = "https://hogarcastillos.queenbeesoftware.com/";
     
     
    //métodos que llaman a los endpoints de la API de wordpress
     
     
    //inicio de sesión en WP
    public static String login(String username, String password) throws IOException {
        URL url = new URL(base_url + "/wp-json/jwt-auth/v1/token");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        String jsonInputString = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
         BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),
 "utf-8"));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONObject jsonResponse = new JSONObject(response.toString());
        return jsonResponse.getString("token");
    }
    
 
    
    // CRUD de Clientes
    public static void createClient(String name, String ci, String phone, String
        email, String address) throws IOException {
           URL url = new URL(base_url + "/wp-json/residencial/v1/clients");
           HttpURLConnection connection = (HttpURLConnection) url.openConnection();
           connection.setRequestMethod("POST");
           connection.setRequestProperty("Authorization", "Bearer " + token);
           connection.setRequestProperty("Content-Type", "application/json");
           connection.setDoOutput(true);
            String jsonInputString = String.format("{\"name\": \"%s\", \"ci\": \"%s\", \"phone\":\"%s\", \"email\": \"%s\", \"address\": \"%s\"}",
            name, ci, phone, email, address);
    try (OutputStream os = connection.getOutputStream()) {
        byte[] input = jsonInputString.getBytes("utf-8");
        os.write(input, 0, input.length);
    }
    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),
 "utf-8"));
    String inputLine;
    StringBuffer response = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
    }
    in.close();
    System.out.println("Response: " + response.toString());
 }

    public static void updateClient(int clientId, String name, String idCard, String
        phone, String email, String address) throws IOException {
           URL url = new URL(base_url +"/wp-json/residencial/v1/clients/" + clientId);
           HttpURLConnection connection = (HttpURLConnection) url.openConnection();
           connection.setRequestMethod("PUT");
           connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Content-Type", "application/json");
    connection.setDoOutput(true);
    String jsonInputString = String.format("{\"name\": \"%s\", \"id_card\": \"%s\", \"phone\":\"%s\", \"email\": \"%s\", \"address\": \"%s\"}",
            name, idCard, phone, email, address);
    try (OutputStream os = connection.getOutputStream()) {
        byte[] input = jsonInputString.getBytes("utf-8");
        os.write(input, 0, input.length);
    }
    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),
 "utf-8"));
    String inputLine;
    StringBuffer response = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
    }
    in.close();
    System.out.println("Response: " + response.toString());
 }
    
 public static void deleteClient(int clientId) throws IOException {
    URL url = new URL(base_url + "/wp-json/residencial/v1/clients/" + clientId);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("DELETE");
    connection.setRequestProperty("Authorization", "Bearer " + token);
    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
    }
    in.close();
    System.out.println("Response: " + response.toString());
 }
    
     public static void listClients() throws IOException {
            URL url = new URL(base_url + "/wp-json/residencial/v1/clients");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println("Clients: " + response.toString());
     }
     
     
 // CRUD de Residentes
    public static void createResident(String name, String ci, String birthDate, String entryDate, String familyName, String familyPhone) throws IOException {
        URL url = new URL(base_url+ "/wp-json/residencial/v1/residents");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer "+token);
        connection.setDoOutput(true);

        // Crear el JSON con los datos del residente
        String jsonInputString = String.format(
            "{" +
            "\"name\": \"%s\"," +
            "\"ci\": \"%s\"," +
            "\"birth_date\": \"%s\"," +
            "\"entry_date\": \"%s\"," +
            "\"family_name\": \"%s\"," +
            "\"family_phone\": \"%s\"" +
            "}",
            name, ci, birthDate, entryDate, familyName, familyPhone
        );

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
            System.out.println("Residente creado exitosamente.");
        } else {
            System.out.println("Error al crear el residente: " + responseCode);
        }
 }

    public static void updateResident(String id, String name, String ci, String birthDate, String entryDate, String familyName, String familyPhone) throws IOException {
        URL url = new URL(base_url + "/wp-json/residencial/v1/residents/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer TU_TOKEN_DE_AUTENTICACION");
        connection.setDoOutput(true);

        // Crear el JSON con los datos actualizados
        String jsonInputString = String.format(
            "{" +
            "\"name\": \"%s\"," +
            "\"ci\": \"%s\"," +
            "\"birth_date\": \"%s\"," +
            "\"entry_date\": \"%s\"," +
            "\"family_name\": \"%s\"," +
            "\"family_phone\": \"%s\"" +
            "}",
            name, ci, birthDate, entryDate, familyName, familyPhone
        );

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("Residente actualizado exitosamente.");
        } else {
            System.out.println("Error al actualizar el residente: " + responseCode);
        }
    }

    public static void listResidents() throws IOException {
        URL url = new URL(base_url + "/wp-json/residencial/v1/residents");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " +token);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Residentes: " + response.toString());
            }
        } else {
            System.out.println("Error al listar los residentes: " + responseCode);
        }
    }
    

    public static void deleteResident(int residentId) throws IOException {
        URL url = new URL(base_url + "/wp-json/residencial/v1/residents/" + residentId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("Authorization", "Bearer " + token);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println("Response: " + response.toString());
}


// CRUD de Recibos (el código de recibo lo maneja la API de forma automática
    
    
   // Crear recibo
    public static void createReceipt(String clientId, String residentId, String amount, String concept) throws IOException {
        // Configuración de la conexión
        URL url = new URL(base_url + "/wp-json/residencial/v1/receipts");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setRequestProperty("Content-Type", "application/json");

        // Generar el cuerpo JSON
        String jsonInputString = String.format(
            "{ \"client_id\": \"%s\", \"resident_id\": %s, \"amount\": \"%s\", \"concept\": \"%s\" }",
            clientId,
            residentId != null ? residentId.toString() : "null",
            amount,
            concept
        );

        // Enviar datos al servidor
        connection.setDoOutput(true);
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Leer respuesta del servidor
        int responseCode = connection.getResponseCode();
        System.out.println("POST Response Code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_CREATED) { // HTTP 201
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                System.out.println("Receipt Created: " + response.toString());
            }
        } else {
            System.out.println("Failed to create receipt. Response Code: " + responseCode);
        }
    }

    // Actualizar recibo
    public static void updateReceipt(String receiptId,String clientId, String residentId, String amount, String concept) throws IOException {
        // Configuración de la conexión
        URL url = new URL(base_url + "/wp-json/residencial/v1/receipts/" + receiptId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setRequestProperty("Content-Type", "application/json");

        // Generar el cuerpo JSON
        String jsonInputString = String.format(
            "{ \"client_id\": \"%s\", \"resident_id\": \"%s\", \"amount\": \"%s\", \"concept\": \"%s\" }",
            clientId,
            residentId != null ? residentId.toString() : "null",
            amount,
            concept
        );

        // Enviar datos al servidor
        connection.setDoOutput(true);
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Leer respuesta del servidor
        int responseCode = connection.getResponseCode();
        System.out.println("PUT Response Code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // HTTP 200
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                System.out.println("Receipt Updated: " + response.toString());
            }
        } else {
            System.out.println("Failed to update receipt. Response Code: " + responseCode);
        }
    }
    
public static void deleteReceipt(int receiptId) throws IOException {
    URL url = new URL(base_url + "/wp-json/residencial/v1/receipts/" + receiptId);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("DELETE");
    connection.setRequestProperty("Authorization", "Bearer " + token);

    int responseCode = connection.getResponseCode();
    System.out.println("DELETE Response Code: " + responseCode);
    if (responseCode == HttpURLConnection.HTTP_OK) { // HTTP 200
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println("Receipt Deleted: " + response.toString());
    } else {
        System.out.println("Failed to delete receipt.");
    }
}
 
public static void listReceipts() throws IOException {
    URL url = new URL(base_url + "/wp-json/residencial/v1/receipts");
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    connection.setRequestProperty("Authorization", "Bearer " + token);

    int responseCode = connection.getResponseCode();
    System.out.println("GET Response Code: " + responseCode);
    if (responseCode == HttpURLConnection.HTTP_OK) { // HTTP 200
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println("Receipts List: " + response.toString());
    } else {
        System.out.println("Failed to retrieve receipts.");
    }
}

    

    
    
//Programa de pruebas
    
    public static void main(String[] args) {
        try {
            token = login("admin", "admintecnico123");
           /*
            createResident("Marta", "321456", "1960-10-10", "2024-23-11", "Víctor", "123456, 9900022");
            createClient("Andrrew", "490898", "290111111", "vicanmendez@gmail.com", "Rocha 999");
            createClient("Pepe", "12345", "123456", "pepe1234234234@gmail.com", "Formoso 1422");
            createClient("Juan", "56767567", "345345", "juan234324@gmail.com", "Castillos esq 19 de Abril");
            createClient("Ana", "77777", "234234324", "anita3453454354367787888@gmail.com", "Chile 1212");
           createResident("Marta", "121212", "19550-10-10", "2024-23-11", "Angelina ", "998");
           createResident("Julian", "01010101", "1966-10-10", "2024-23-11", "Angelina, Juan", "121212");
           createResident("Carlos Juan", "321456", "1960-11-10", "2024-23-11", "Víctor", "01010101");
           createReceipt("3", "2", "500", "sociedad");
           createReceipt("3", "1", "500", "sociedad");
           createReceipt("1", "2", "500", "mensualidad");
           */

           
            createReceipt("1", "2", "100", "mensualidad");

            listClients();
            listResidents();
            listReceipts();
        } catch (IOException ex) {
            Logger.getLogger(APIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 }
