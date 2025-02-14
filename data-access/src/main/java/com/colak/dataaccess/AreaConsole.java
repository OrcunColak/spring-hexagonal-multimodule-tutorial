package com.colak.dataaccess;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "AreaConsoles")
@Getter
@Setter
public class AreaConsole {

    @Id
    @Column(name = "ConsoleID")
    private Integer consoleID;

    @Column(name = "ConsoleName")
    private String consoleName;


}
