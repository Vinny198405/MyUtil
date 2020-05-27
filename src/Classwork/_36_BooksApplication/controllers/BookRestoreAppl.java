package Classwork._36_BooksApplication.controllers;

import Classwork._36_BooksApplication.dto.Book;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class BookRestoreAppl {

    public static void main(String[] args) throws Exception{
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream("books.data"))){
            @SuppressWarnings("unchecked")
            List<Book> books = (List<Book>) input.readObject();
            books.forEach(System.out::println);
        }


    }

}
