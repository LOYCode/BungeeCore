package io.loyloy.bungeecore.pm;

import io.loyloy.bungeecore.BungeeCore;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.UUID;

public class ReplyCommand extends Command
{
    private BungeeCore plugin;
    private MessageManager messMan;

    public ReplyCommand( BungeeCore plugin, MessageManager messMan )
    {
        super( "reply", null, "r" );

        this.plugin = plugin;
        this.messMan = messMan;
    }

    @Override
    public void execute( CommandSender commandSender, String[] args)
    {
        ProxiedPlayer sender = (ProxiedPlayer) commandSender;

        if( args.length < 1 )
        {
            sender.sendMessage( BungeeCore.getPfx().append( "Reply with /r <message>" ).color( ChatColor.RED ).create() );
            return;
        }

        if( ! messMan.isTalking( sender.getUniqueId() ) )
        {
            sender.sendMessage( BungeeCore.getPfx().append( "You have not been messaging recently..." ).color( ChatColor.RED ).create() );
            return;
        }

        UUID receiverUUID = messMan.getReceiver( sender.getUniqueId() );
        ProxiedPlayer receiver = plugin.getProxy().getPlayer( receiverUUID );

        if( receiver == null )
        {
            sender.sendMessage( BungeeCore.getPfx().append( "That player is not online anymore :(" ).color( ChatColor.RED ).create() );
            return;
        }

        String content = "";
        for( int i=0 ; i < args.length ; i++ )
        {
            content += args[i] + " ";
        }

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

        messMan.updateTakler( receiver.getUniqueId(), sender.getUniqueId() );
    }
}
