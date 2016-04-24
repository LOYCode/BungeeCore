package io.loyloy.bungeecore.pm;

import io.loyloy.bungeecore.BungeeCore;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;
import java.util.UUID;

public class MessageManager
{
    private BungeeCore plugin;
    private HashMap<UUID, UUID> talkers;

    public MessageManager( BungeeCore plugin )
    {
        this.plugin = plugin;
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

    public ProxiedPlayer getReceiver( UUID sender )
    {
        return plugin.getProxy().getPlayer( talkers.get( sender ) );
    }

    public void send( String content, ProxiedPlayer sender, ProxiedPlayer receiver )
    {
        sender.sendMessage( new ComponentBuilder( "You" ).color( ChatColor.WHITE )
                .append( " >> " ).color( ChatColor.YELLOW )
                .append( receiver.getDisplayName() ).color( ChatColor.WHITE )
                .append( " " + content ).color( ChatColor.YELLOW )
                .create() );

        receiver.sendMessage( new ComponentBuilder( sender.getDisplayName() ).color( ChatColor.WHITE )
                .append( " >> " ).color( ChatColor.YELLOW )
                .append( "You" ).color( ChatColor.WHITE )
                .append( " " + content ).color( ChatColor.YELLOW )
                .create() );

        plugin.getLogger().info( "[PM] " + sender.getName() + " -> " + receiver.getName() + ": " + content );
    }
}