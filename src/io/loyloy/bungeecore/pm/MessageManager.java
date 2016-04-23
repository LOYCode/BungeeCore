package io.loyloy.bungeecore.pm;

import java.util.HashMap;
import java.util.UUID;

public class MessageManager
{
    private HashMap<UUID, UUID> talkers;

    public MessageManager()
    {
        this.talkers = new HashMap<>();
    }

    public void updateTakler( UUID sender, UUID receiver )
    {
        talkers.put( sender, receiver );
    }

    public boolean isTalking( UUID sender )
    {
        return talkers.containsKey( sender );
    }

    public UUID getReceiver( UUID sender )
    {
        return talkers.get( sender );
    }
}