package ru.ssau.transport.visitors;

import ru.ssau.transport.Auto;
import ru.ssau.transport.Motorcycle;

public interface TransportVisitor
{
    void visit(Auto auto);
    void visit(Motorcycle motorcycle);
}
