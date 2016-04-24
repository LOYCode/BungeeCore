package io.loyloy.bungeecore.commands;

import io.loyloy.bungeecore.BungeeCore;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class GlobalBroadcastCommand extends Command
{
    private BungeeCore plugin;

    public GlobalBroadcastCommand( BungeeCore plugin )
    {
        super( "globalbroadcast", "loy.gbroadcast", "gbroadcast" );

        this.plugin = plugin;
    }

    @Override
    public void execute( CommandSender commandSender, String[] args)
    {
        if( args.length < 1 )
        {
            commandSender.sendMessage( BungeeCore.getPfx().append( "Global broadcast with /gbroadcast <message>" ).color( ChatColor.RED ).create() );
            return;
        }

        String content = "";
        for( int i=0 ; i < args.length ; i++ )
        {
            content += args[i] + " ";
        }
        content = ChatColor.translateAlternateColorCodes( '&', content );
        content = ChatColor.RED + "[LOY] " + ChatColor.WHITE + commandSender.getName() + ChatColor.RED + ": " + content;

        plugin.getProxy().broadcast( new TextComponent( content ) );

        int playerCount = plugin.getProxy().getOnlineCount();
        int serverCount = plugin.getProxy().getServers().size();
        commandSender.sendMessage( BungeeCore.getPfx().append( "Broadcast sent to " + playerCount + " players on " + serverCount + " servers." ).color( ChatColor.RED ).create() );
    }
}