import CRUD.BookCRUD;
import model.Book;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        int choose;
        Scanner read=new Scanner(System.in);
        String title, autor;
        BookCRUD bookCRUD=new BookCRUD();


        while(true){
            System.out.print("1-add ,2-delete ,3-update,4-show,5-exit\n");
            choose=read.nextInt();
            if(choose==5){
                break;
            }

            switch(choose){

                case 1:


                    System.out.print("Title:\n");

                    title=read.next();

                    System.out.print("Author:\n");

                    autor=read.next();


                    bookCRUD.create(new Book(title,autor));
                    break;

                case 2:
                    System.out.print("ID:\n");
                    choose=read.nextInt();

                    bookCRUD.delete(choose);
                    break;
                case 3:
                    System.out.print("ID:\n");
                    choose=read.nextInt();
                    System.out.print("New title::\n");
                    title=read.next();
                    bookCRUD.update(choose,title);
                    break;

                case 4:
                    bookCRUD.read();

                    break;

                default:
                    System.out.print("wrong value");
                    break;

            }

        }





    }
}
