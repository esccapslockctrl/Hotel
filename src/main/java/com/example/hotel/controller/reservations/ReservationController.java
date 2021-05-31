package com.example.hotel.controller.reservations;

import com.example.hotel.controller.reservations.dto.in.ReservationCreateDto;
import com.example.hotel.controller.reservations.dto.out.ReservationDto;
import com.example.hotel.model.reservations.Reservation;
import com.example.hotel.service.reservations.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    @PostMapping("create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addNewReservation (@RequestBody ReservationCreateDto reservationCreateDto) {
        reservationService.create(reservationMapper.fromDto(reservationCreateDto));
    }

    @PostMapping("{id}/update")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateReservation (@RequestBody ReservationCreateDto reservationCreateDto,
                                   @PathVariable("id") Integer id) {
        reservationService.update(reservationMapper.fromDto(reservationCreateDto), id);
    }

    @PostMapping("{id}/delete")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteReservation(@PathVariable("id") Integer id) {
        reservationService.delete(id);
    }

    @GetMapping("{id}")
    public ReservationDto getAtReservation(@PathVariable("id") Integer id) {
        Reservation reservation = reservationService.findAt(id);
        ReservationDto reservationDto = reservationMapper.toDto(reservation);
        return reservationDto;
    }

    @GetMapping("list")
    public List<ReservationDto> getAllReservations() {
        return reservationMapper.toList(reservationService.findAll());
    }

    @GetMapping("search/name")
    public List<ReservationDto> getReservationsByName(@RequestParam("firstName") String firstName,
                                                      @RequestParam("secondName") String secondName) {
        return reservationMapper.toList(reservationService.findByName(firstName, secondName));
    }

    @GetMapping("search/date")
    public List<ReservationDto> getReservationsByDate(@RequestParam("beginDate")
                                                      @DateTimeFormat(pattern="yyyy-MM-dd") Date beginDate) {
        return reservationMapper.toList(reservationService.findByBeginDate(beginDate));
    }
}
