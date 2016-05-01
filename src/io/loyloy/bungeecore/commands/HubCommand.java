package io.loyloy.bungeecore.commands;

import io.loyloy.bungeecore.BungeeCore;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class HubCommand extends Command
{
    public HubCommand()
    {
        super( "hub", "loy.hub" );
    }

    @Override
    public void execute( CommandSender commandSender, String[] args)
    {
        if( !(commandSender instanceof ProxiedPlayer) )
        {
            commandSender.sendMessage( BungeeCore.getPfx().append( "You must be a player to do that!" ).create() );
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) commandSender;

        if( player.getServer().getInfo().getName().equalsIgnoreCase( "hub" ) )
        {
            commandSender.sendMessage( BungeeCore.getPfx().append( "You're on the hub already!" ).create() );
            return;
        }

        player.connect( ProxyServer.getInstance().getServerInfo( "hub" ) );
    }
}