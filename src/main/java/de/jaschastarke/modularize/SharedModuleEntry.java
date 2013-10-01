package de.jaschastarke.modularize;

import java.util.ArrayList;
import java.util.List;

public class SharedModuleEntry<T extends IModule> extends ModuleEntry<T> {
    protected List<ModuleEntry<?>> usingModules = new ArrayList<ModuleEntry<?>>();
    
    public SharedModuleEntry(final T module, final ModuleManager manager) {
        super(module, manager);
        setEnabled(false);
    }

    public SharedModuleEntry(final T module) {
        super(module);
        setEnabled(false);
    }
    
    public void addDepending(final ModuleEntry<?> entry) {
        usingModules.add(entry);
        updateState();
    }
    public void removeDepending(final ModuleEntry<?> entry) {
        usingModules.remove(entry);
        updateState();
    }
    public void updateState() {
        boolean needed = false;
        for (ModuleEntry<?> entry : usingModules) {
            if (entry.isEnabled()) {
                needed = true;
                break;
            }
        }
        if (needed) {
            enable();
        } else {
            disable();
        }
    }
}
