/*
 * This file is part of ViaForge - https://github.com/FlorianMichael/ViaForge
 * Copyright (C) 2021-2026 FlorianMichael/EnZaXD <git@florianmichael.de> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.florianmichael.viaforge.platform;

import com.viaversion.viarewind.protocol.v1_7_6_10to1_7_2_5.packet.ClientboundPackets1_7_2_5;
import com.viaversion.viarewind.protocol.v1_7_6_10to1_7_2_5.packet.ServerboundPackets1_7_2_5;
import de.florianmichael.viaforge.common.platform.ViaForgeProtocolBase;

public final class ViaForgeProtocol extends ViaForgeProtocolBase<ClientboundPackets1_7_2_5, ClientboundPackets1_7_2_5, ServerboundPackets1_7_2_5, ServerboundPackets1_7_2_5> {

    public static final ViaForgeProtocol INSTANCE = new ViaForgeProtocol();

    public ViaForgeProtocol() {
        super(ClientboundPackets1_7_2_5.class, ClientboundPackets1_7_2_5.class, ServerboundPackets1_7_2_5.class, ServerboundPackets1_7_2_5.class);
    }

}
