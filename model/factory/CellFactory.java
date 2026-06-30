package model.factory;

import model.Cell;

public interface CellFactory {
    // Maakt nieuwe cel aan
    Cell createCell();
    // Weet wanneer cel geboren mag worden
    boolean shouldBeBorn(int neighbors);
}
