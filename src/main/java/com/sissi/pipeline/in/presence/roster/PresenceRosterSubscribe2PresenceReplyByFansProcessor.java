package com.sissi.pipeline.in.presence.roster;

import com.sissi.context.JID;
import com.sissi.context.JIDContext;
import com.sissi.protocol.Protocol;
import com.sissi.protocol.iq.roster.RosterSubscription;
import com.sissi.protocol.presence.Presence;
import com.sissi.protocol.presence.PresenceType;

/**
 * @author kim 2014年1月24日
 */
public class PresenceRosterSubscribe2PresenceReplyByFansProcessor extends PresenceRoster2ItemProcessor {

	@Override
	public Boolean input(JIDContext context, Protocol protocol) {
		JID to = super.build(protocol.getTo());
		RosterSubscription relation = RosterSubscription.parse(super.ourRelation(to, context.getJid()).getSubscription());
		return relation == RosterSubscription.FROM || relation == RosterSubscription.BOTH ? this.writeAndReturn(context, protocol, to) : true;
	}

	private Boolean writeAndReturn(JIDContext context, Protocol protocol, JID to) {
		// deep copy
		for (String resource : super.resources(to)) {
			super.broadcast(context.getJid(), super.prepare(context.getJid(), to));
			super.broadcast(context.getJid(), new Presence().setFrom(to.setResource(resource)).setType(PresenceType.SUBSCRIBED));
			super.broadcast(context.getJid(), new Presence().setFrom(to.setResource(resource)).setType(PresenceType.AVAILABLE));
		}
		return false;
	}
}
