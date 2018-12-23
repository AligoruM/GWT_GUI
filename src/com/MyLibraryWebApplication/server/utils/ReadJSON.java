package com.MyLibraryWebApplication.server.utils;

public class ReadJSON {
    public static void main(String[] args) {
        /*final List<Book> BOOK_LIST = Arrays.asList(
                new Book(0, "Dmitry Glukhovsky", "Metro 2033", 480, new Date(), new Date()),
                new Book(1, "Dmitry Glukhovsky", "Metro 2035", 435, new Date(), new Date()),
                new Book(2, "Dmitry Glukhovsky", "Metro 2034", 542, new Date(), new Date()));
        List<Book> books = null;

        try (Writer writer = new FileWriter("data.json")){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(BOOK_LIST, writer);
        }catch(IOException e){
            e.printStackTrace();
        }

        try(Reader reader = new FileReader("data.json")){
            Gson gson = new Gson();
            books = gson.fromJson(reader, new TypeToken<List<Book>>(){}.getType());
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println(books);*/

    }
}
