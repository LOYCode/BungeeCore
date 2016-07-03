package io.loyloy.bungeecore;

import io.loyloy.bungeecore.commands.GlobalBroadcastCommand;
import io.loyloy.bungeecore.commands.HubCommand;
import io.loyloy.bungeecore.pm.MessageCommand;
import io.loyloy.bungeecore.pm.MessageManager;
import io.loyloy.bungeecore.pm.ReplyCommand;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class BungeeCore extends Plugin
{
    private static final ComponentBuilder PREFIX = new ComponentBuilder( "[Loy]" ).color( ChatColor.YELLOW ).append( " " ).color( ChatColor.GREEN );

    @Override
    public void onEnable()
    {
        PluginManager pluginManager = getProxy().getPluginManager();

        //Misc
        pluginManager.registerListener( this, new BungeeListener() );

        pluginManager.registerCommand( this, new GlobalBroadcastCommand( this ) );
        pluginManager.registerCommand( this, new HubCommand() );

        //Private Messaging
        //MessageManager messMan =  new MessageManager( this );
        //pluginManager.registerCommand( this, new MessageCommand( this, messMan ) );
        //pluginManager.registerCommand( this, new ReplyCommand( messMan ) );
    }

    public static ComponentBuilder getPfx()
    {
        return new ComponentBuilder( PREFIX );
    }
}