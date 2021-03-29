package org.int32_t.utils;

import org.int32_t.models.Client;
import java.util.List;

public interface Strategy {
    void addTask(List<Server> servers, Client c);
}
