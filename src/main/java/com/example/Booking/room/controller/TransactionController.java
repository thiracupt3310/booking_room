package com.example.Booking.room.controller;

import com.example.Booking.room.model.RoomMap;
import com.example.Booking.room.model.Transaction;
import com.example.Booking.room.service.TransactionService;
import com.example.Booking.room.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/booking")
public class TransactionController {

    private TransactionService transactionService;
    private List<RoomMap> roomMapList;
    private String date;
    private int timeindex;

    public TransactionController(TransactionService transactionService, UserService userService) {
        this.transactionService = transactionService;
        roomMapList = new ArrayList<RoomMap>();

    // inizialize minimap for show in bookingpage
        roomMapList.add(new RoomMap("M365.25,274c-15,0-30,0-45,.05-1.87,0-2.31-.43-2.31-2.3q.1-40.75,0-81.5c0-1.86.43-2.3,2.3-2.3q45,.11,90,0c1.86,0,2.3.43,2.3,2.3q-.1,40.75,0,81.5c0,1.86-.43,2.31-2.3,2.3C395.25,274,380.25,274,365.25,274Z", "translate(-0.47 -0.46)", "350", "235"));
        roomMapList.add(new RoomMap("M466.25,188c16.33,0,32.66,0,49-.05,1.87,0,2.31.43,2.31,2.3q-.1,40.75,0,81.49c0,1.87-.43,2.31-2.3,2.31q-49-.1-98,0c-1.87,0-2.31-.43-2.31-2.3q.1-40.75,0-81.49c0-1.87.43-2.32,2.3-2.31C433.58,188,449.92,188,466.25,188Z" ,"translate(-0.47 -0.46)", "450", "235"));
        roomMapList.add(new RoomMap("M565.25,274c-14.33,0-28.66,0-43,.05-1.86,0-2.3-.43-2.3-2.3q.1-40.75,0-81.5c0-1.86.43-2.3,2.3-2.3q43,.11,86,0c1.86,0,2.3.43,2.3,2.3q-.1,40.75,0,81.5c0,1.86-.43,2.31-2.3,2.3C593.92,274,579.58,274,565.25,274Z", "translate(-0.47 -0.46)", "550", "235"));
        roomMapList.add(new RoomMap("M540.5,3.5c22.5,0,45,0,67.5,0,2.07,0,2.56.47,2.55,2.55q-.1,48.5,0,97c0,2.07-.48,2.55-2.55,2.55q-67.5-.1-135,0c-2.07,0-2.56-.47-2.55-2.55q.1-48.49,0-97c0-2.07.48-2.56,2.55-2.55C495.5,3.53,518,3.5,540.5,3.5Z" ,"translate(-0.47 -0.46)", "525", "55"));
        roomMapList.add(new RoomMap("M392,105.5c-24.5,0-49,0-73.5,0-2.07,0-2.56-.47-2.55-2.55q.1-48.49,0-97c0-2.08.48-2.55,2.55-2.55q73.5.09,147,0c2.07,0,2.56.47,2.55,2.55q-.1,48.5,0,97c0,2.08-.48,2.56-2.55,2.55C441,105.47,416.5,105.5,392,105.5Z" ,"translate(-0.47 -0.46)", "380", "55"));
        roomMapList.add(new RoomMap("M129,3.5q61.62,0,123.25,0c2.26,0,2.81.54,2.8,2.8q-.12,48.14,0,96.25c0,2.08-.48,2.55-2.56,2.55q-123.49-.09-247,0c-2.07,0-2.56-.48-2.55-2.55Q3.06,54.25,3,6c0-2.08.48-2.56,2.56-2.55Q67.25,3.56,129,3.5Z" ,"translate(-0.47 -0.46)", "115", "55"));
    }

//    @GetMapping
//    public String getBookingPage(Model model) {
//        model.addAttribute("allTransaction", this.transactionService.getTransaction());
//        model.addAttribute("allRoomMap", this.roomMapList);
//        return "booking";
//    }


    @GetMapping("/{username}&{date}&{timeIndex}")
    public String getBookingTest(Model model, @PathVariable String date, @PathVariable int timeIndex, @PathVariable String username){
        // add timelist for dropdown
        List<String> times = Arrays.asList("08:00:00 - 09:00:00", "09:00:00 - 10:00:00",
                "10:00:00 - 11:00:00", "11:00:00 - 12:00:00", "12:00:00 - 13:00:00",
                "13:00:00 - 14:00:00", "14:00:00 - 15:00:00", "15:00:00 - 16:00:00",
                "16:00:00 - 17:00:00", "17:00:00 - 18:00:00");
        if (this.transactionService.getTransactions(date, timeIndex).isEmpty()){
            model.addAttribute("test", "something wrong");
        }
        model.addAttribute("allTransaction", this.transactionService.getTransactions(date, timeIndex));
        model.addAttribute("allRoomMap", this.roomMapList);
        model.addAttribute("times", times);
        model.addAttribute("timeIndex", timeIndex);
        model.addAttribute("date", date);
        model.addAttribute("username", username);
        model.addAttribute("transaction", this.transactionService.getUserTransaction(username));


        this.date = date;
        this.timeindex = timeIndex;

        return "booking";
    }

    @PostMapping("/{username}&{date}&{timeIndex}")
    public String editAccount(Transaction transaction,
                              Model model, @PathVariable String username) {

        transactionService.editTransaction(transaction);

        return "redirect:/booking/" + username + "&" + this.date + "&" + this.timeindex;
    }

    @GetMapping("/cancel/{username}")
    public String getCancelPage(@PathVariable String username, Model model){
        model.addAttribute("transaction", this.transactionService.getUserTransaction(username));
        model.addAttribute("date", this.date);

        return "cancel";
    }
    @PostMapping("/cancel/{username}")
    public String confirmCancel(@PathVariable String username, Transaction transaction){
        transactionService.editTransaction(transaction);

        return "redirect:/booking/" + username + "&" + this.date + "&" + this.timeindex;
    }
}
