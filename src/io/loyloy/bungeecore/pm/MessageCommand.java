package io.loyloy.bungeecore.pm;

import io.loyloy.bungeecore.BungeeCore;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Collection;

public class MessageCommand extends Command
{
    private BungeeCore plugin;
    private MessageManager messMan;

    public MessageCommand( BungeeCore plugin, MessageManager messMan )
    {
        super( "message", null, "msg", "pm", "tell", "whisper" );

        this.plugin = plugin;
        this.messMan = messMan;
    }

    @Override
    public void execute( CommandSender commandSender, String[] args)
    {
        ProxiedPlayer sender = (ProxiedPlayer) commandSender;

        if( args.length < 2 )
        {
            sender.sendMessage( BungeeCore.getPfx().append( "Send a pm with /pm <name> <message>" ).color( ChatColor.GREEN ).create() );
            return;
        }

        ProxiedPlayer receiver = null;
        Collection<ProxiedPlayer> players = plugin.getProxy().getPlayers();

        for( ProxiedPlayer player : players )
        {
            if( player.getDisplayName().contains( args[0] ) )
            {
                receiver = player;
                break;
            }
        }

        if( receiver == null )
        {
            sender.sendMessage( BungeeCore.getPfx().append( "Sorry, no one with a name like " + args[0] + " is online!" ).color( ChatColor.RED ).create() );
            return;
        }

        if( receiver.getUniqueId().equals( sender.getUniqueId() ) )
        {
            sender.sendMessage( BungeeCore.getPfx().append( "You don't need to pm yourself!" ).color( ChatColor.RED ).create() );
            return;
        }

        String content = "";
        for( int i=1 ; i < args.length ; i++ )
        {
            content += args[i] + " ";
        }

        messMan.send( content, sender, receiver );

        messMan.updateTakler( sender.getUniqueId(), receiver.getUniqueId() );
        messMan.updateTakler( receiver.getUniqueId(), sender.getUniqueId() );
    }
}