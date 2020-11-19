package com.book.main;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;


@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
public class MyUITest {

    @Autowired
    BookRepository rep;

    @Autowired
    UserRepository urep;

    @InjectMocks
    BookForm bkForm;

    VaadinRequest vaadinRequest = Mockito.mock(VaadinRequest.class);

    private MyUI ui;

    @Mock
    private Page page;

    private Book bk;

    @Mock
    private VaadinRequest req;

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void setup() throws Exception {
        this.ui = new MyUI(rep, urep);          
        ui.setBookRep(rep);
        ui.setUserRep(urep);
        initUi();
        mockPage();        
        this.bkForm = new BookForm(ui, rep);
    }

    private void initUi() {
        ui.init(req);
    }

    private void mockPage() throws NoSuchFieldException, IllegalAccessException {
        Field pageField = UI.class.getDeclaredField("page");
        pageField.setAccessible(true);
        pageField.set(ui, page);
    }

    @Test
    public void testbooksave() {
        try {
        	bookRepository.deleteAll();
            if (ui.getForm() != null) {
                bk = new Book();
                bk.setId(new Long(1));
                bk.setAuthorName("Author");
                bk.setDescription("Desc");
                bk.setPrice(1000);
                bk.setPublicationDate(new SimpleDateFormat("yyyy-MM-dd").parse("1978-12-12"));
                bk.setTitle("Title");
                bkForm.setBook(bk);
                bkForm.getSave().click();
                // bkForm.isVisible();
            }

            List<Book> bookList = new ArrayList<Book>();
            bookRepository.findAll().forEach(bookList::add);                       
            Book book = bookList.get(0);            
            assertEquals("Title", book.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testbookDelete() {
        try {
            if (ui.getForm() != null) {
                bk = new Book();
                bk.setId(new Long(2));
                bk.setAuthorName("Author1");
                bk.setDescription("Desc1");
                bk.setPrice(1000);
                bk.setPublicationDate(new SimpleDateFormat("yyyy-MM-dd").parse("1978-12-12"));
                bk.setTitle("Title1");
                bkForm.setBook(bk);
                bkForm.getSave().click();
                // bkForm.isVisible();
            }
            List<Book> bookList = new ArrayList<Book>();
            bookRepository.findAll().forEach(bookList::add);
            Book book = bookList.get(0);
            assertEquals(2, bookList.size());

            if (ui.getForm() != null) {
                bk = new Book();
                bk.setId(book.getId());
                bk.setAuthorName("Author");
                bk.setDescription("Desc");
                bk.setPrice(1000);
                bk.setPublicationDate(new SimpleDateFormat("yyyy-MM-dd").parse("1978-12-12"));
                bk.setTitle("Title");
                bkForm.setBook(bk);
                bkForm.getDelete().click();
            }

            List<Book> bookList1 = new ArrayList<Book>();
            bookRepository.findAll().forEach(bookList1::add);
            assertEquals(1, bookList1.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testbookAddButton() {
        assertFalse(ui.getAddBookBtn() == null);
        if (ui.getAddBookBtn() != null) {
            ui.getAddBookBtn().click();
        }
    }

    @Test
    public void testFields() {
        assertFalse(ui.getFilterText() == null);
        assertFalse(ui.getClearFilterTextBtn() == null);
        assertFalse(ui.getGrid() == null);
    }

    @Test
    public void testVisbility() {

        if (ui.getFilterText() != null) {
            assertTrue(ui.getFilterText().isVisible());
        }
        if (ui.getClearFilterTextBtn() != null) {
            assertTrue(ui.getClearFilterTextBtn().isVisible());
        }

        if (ui.getAddBookBtn() != null) {
            assertTrue(ui.getAddBookBtn().isVisible());
        }
        if (ui.getGrid() != null) {
            assertTrue(ui.getGrid().isVisible());

        }

    }
}