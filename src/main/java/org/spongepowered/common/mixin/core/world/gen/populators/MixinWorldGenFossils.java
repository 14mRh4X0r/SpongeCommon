/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.common.mixin.core.world.gen.populators;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenFossils;
import net.minecraft.world.gen.feature.WorldGenerator;
import org.spongepowered.api.world.chunk.ProtoChunk;
import org.spongepowered.api.world.ProtoWorld;
import org.spongepowered.api.world.gen.PopulatorType;
import org.spongepowered.api.world.gen.PopulatorTypes;
import org.spongepowered.api.world.gen.populator.Fossil;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(WorldGenFossils.class)
public abstract class MixinWorldGenFossils extends WorldGenerator implements Fossil {

    private double chance = 1 / 64.0;

    @Override
    public PopulatorType getType() {
        return PopulatorTypes.FOSSIL;
    }

    @Override
    public void populate(ProtoWorld<?> world, ProtoChunk volume, Random random) {
        if (random.nextDouble() > this.chance) {
            return;
        }
        int x = volume.getBlockMin().getX();
        int z = volume.getBlockMin().getZ();
        generate((net.minecraft.world.World) world, random, new BlockPos((x >> 4) << 4, 0, (z >> 4) << 4));
    }

    @Override
    public double getSpawnProbability() {
        return this.chance;
    }

    @Override
    public void setSpawnProbability(double chance) {
        this.chance = chance;
    }

}
