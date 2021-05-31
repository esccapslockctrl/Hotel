package com.example.hotel.controller.rooms.dto.in;

import com.example.hotel.model.rooms.AvailableStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomCreateDto {
    private String pictureName;
    private int storey;
    private int bedCount;
    private int price;
    private AvailableStatus tvStatus;
    private AvailableStatus balconyStatus;
    private AvailableStatus fridgeStatus;
    private AvailableStatus availableStatus;
}
