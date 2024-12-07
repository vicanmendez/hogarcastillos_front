/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;



import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;


 public class CrudApi {
     //Atributos generales
     private static String token = "";
    // private static String base_url = "https://hogarcastillos.queenbeesoftware.com/";
          private static String base_url = "http://localhost/residencial/";

     
     public void setToken(String token) {
         this.token = token;
     }
     
     
     
    //métodos que llaman a los endpoints de la API de wordpress
     
     
    //inicio de sesión en WP
        public static String login(String username,String password) throws IOException {
            URL url = new URL(base_url + "wp-json/jwt-auth/v1/token");
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

         public static String listClients() throws IOException {
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
                return response.toString();
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
            connection.setRequestProperty("Authorization", "Bearer "+token);
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

        public static String listResidents() throws IOException {
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
                    return response.toString();

                }
            } else {
                System.out.println("Error al listar los residentes: " + responseCode);
            }
           return "error";
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
 }
