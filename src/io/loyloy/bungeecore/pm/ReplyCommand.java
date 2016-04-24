package io.loyloy.bungeecore.pm;

import io.loyloy.bungeecore.BungeeCore;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ReplyCommand extends Command
{
    private MessageManager messMan;

    public ReplyCommand( MessageManager messMan )
    {
        super( "reply", null, "r" );

        this.messMan = messMan;
    }

    @Override
    public void execute( CommandSender commandSender, String[] args)
    {
        ProxiedPlayer sender = (ProxiedPlayer) commandSender;

        if( args.length < 1 )
        {
            sender.sendMessage( BungeeCore.getPfx().append( "Reply with /r <message>" ).color( ChatColor.GREEN ).create() );
            return;
        }

        if( ! messMan.isTalking( sender.getUniqueId() ) )
        {
            sender.sendMessage( BungeeCore.getPfx().append( "You have not been messaging recently..." ).color( ChatColor.RED ).create() );
            return;
        }

        ProxiedPlayer receiver = messMan.getReceiver( sender.getUniqueId() );

        if( receiver == null )
        {
            sender.sendMessage( BungeeCore.getPfx().append( "That player is not online anymore :(" ).color( ChatColor.RED ).create() );
            return;
        }

        if( receiver.getUniqueId().equals( sender.getUniqueId() ) )
        {
            sender.sendMessage( BungeeCore.getPfx().append( "You don't need to pm yourself!" ).color( ChatColor.RED ).create() );
            return;
        }

        String content = "";
        for( int i=0 ; i < args.length ; i++ )
        {
            content += args[i] + " ";
        }

        messMan.send( content, sender, receiver );

        messMan.updateTakler( receiver.getUniqueId(), sender.getUniqueId() );
    }
}
