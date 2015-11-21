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
package org.spongepowered.common.data.processor.value.entity;

import net.minecraft.entity.passive.EntityOcelot;
import org.spongepowered.api.data.DataTransactionBuilder;
import org.spongepowered.api.data.DataTransactionResult;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.OcelotType;
import org.spongepowered.api.data.type.OcelotTypes;
import org.spongepowered.api.data.value.ValueContainer;
import org.spongepowered.api.data.value.immutable.ImmutableValue;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.common.data.processor.common.AbstractSpongeValueProcessor;
import org.spongepowered.common.data.value.immutable.ImmutableSpongeValue;
import org.spongepowered.common.data.value.mutable.SpongeValue;
import org.spongepowered.common.entity.SpongeEntityConstants;
import org.spongepowered.common.entity.SpongeOcelotType;

import java.util.Optional;

public class OcelotTypeValueProcessor extends AbstractSpongeValueProcessor<EntityOcelot, OcelotType, Value<OcelotType>> {

    public OcelotTypeValueProcessor() {
        super(EntityOcelot.class, Keys.OCELOT_TYPE);
    }

    @Override
    public DataTransactionResult removeFrom(ValueContainer<?> container) {
        return DataTransactionBuilder.failNoData();
    }

    @Override
    protected Value<OcelotType> constructValue(OcelotType defaultValue) {
        return new SpongeValue<>(Keys.OCELOT_TYPE, defaultValue, OcelotTypes.WILD_OCELOT);
    }

    @Override
    protected boolean set(EntityOcelot container, OcelotType value) {
        if (value instanceof SpongeOcelotType) {
            container.setTameSkin(((SpongeOcelotType) value).type);
            return true;
        }
        return false;
    }

    @Override
    protected Optional<OcelotType> getVal(EntityOcelot container) {
        return Optional.ofNullable(SpongeEntityConstants.OCELOT_IDMAP.get(container.getTameSkin()));
    }
    
    @Override
    protected ImmutableValue<OcelotType> constructImmutableValue(OcelotType value) {
        return ImmutableSpongeValue.cachedOf(Keys.OCELOT_TYPE, OcelotTypes.WILD_OCELOT, value);
    }

}
