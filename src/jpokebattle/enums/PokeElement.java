package jpokebattle.enums;

public enum PokeElement {
    NORMAL,
    FIRE,
    WATER,
    ELECTRIC,
    GRASS,
    ICE,
    FIGHTING,
    POISON,
    GROUND,
    FLYING,
    PSYCHIC,
    BUG,
    ROCK,
    GHOST,
    DRAGON;
    
    public AttackEffectiveness getAttackResultTo(PokeElement other)
    {
        if (this == other)
            return AttackEffectiveness.NOT_EFFECTIVE;

        switch (this)
        {
            case NORMAL:
                if (other == ROCK)
                    return AttackEffectiveness.NOT_EFFECTIVE;
                if (other == GHOST)
                    return AttackEffectiveness.MISS;
                break;
            case FIRE:
                if (other == FIRE || other == WATER || other == ROCK || other == DRAGON)
                    return AttackEffectiveness.NOT_EFFECTIVE;
                if (other == GRASS || other == ICE || other == BUG)
                    return AttackEffectiveness.EFFECTIVE;
                break;
            case WATER:
                if (other == WATER || other == GRASS || other == DRAGON)
                    return AttackEffectiveness.NOT_EFFECTIVE;
                if (other == FIRE || other == GROUND || other == ROCK)
                    return AttackEffectiveness.EFFECTIVE;
                break;
            case ELECTRIC:
                if (other == ELECTRIC || other == GRASS || other == DRAGON)
                    return AttackEffectiveness.NOT_EFFECTIVE;
                if (other == WATER || other == FLYING)
                    return AttackEffectiveness.EFFECTIVE;
                break;
            case GRASS:
                if (other == FIRE || other == GRASS || other == POISON || other == FLYING || other == BUG || other == DRAGON)
                    return AttackEffectiveness.NOT_EFFECTIVE;
                if (other == WATER || other == GROUND || other == ROCK)
                    return AttackEffectiveness.EFFECTIVE;
                break;
            case ICE:
                if (other == FIRE || other == WATER || other == ICE)
                    return AttackEffectiveness.NOT_EFFECTIVE;
                if (other == GRASS || other == GROUND || other == FLYING || other == DRAGON)
                    return AttackEffectiveness.EFFECTIVE;
                break;
            case FIGHTING:
                if (other == POISON || other == FLYING || other == PSYCHIC || other == BUG)
                    return AttackEffectiveness.NOT_EFFECTIVE;
                if (other == NORMAL || other == ICE || other == ROCK)
                    return AttackEffectiveness.EFFECTIVE;
                break;
            case POISON:
                if (other == POISON || other == GROUND || other == ROCK || other == GHOST)
                    return AttackEffectiveness.NOT_EFFECTIVE;
                if (other == GRASS)
                    return AttackEffectiveness.EFFECTIVE;
                break;
            case GROUND:
                if (other == GRASS || other == BUG)
                    return AttackEffectiveness.NOT_EFFECTIVE;
                if (other == FIRE || other == ELECTRIC || other == POISON || other == ROCK)
                    return AttackEffectiveness.EFFECTIVE;
                break;
            case FLYING:
                if (other == ELECTRIC || other == ROCK)
                    return AttackEffectiveness.NOT_EFFECTIVE;
                if (other == GRASS || other == FIGHTING || other == BUG)
                    return AttackEffectiveness.EFFECTIVE;
                break;
            case PSYCHIC:
                if (other == PSYCHIC)
                    return AttackEffectiveness.NOT_EFFECTIVE;
                if (other == FIGHTING || other == POISON)
                    return AttackEffectiveness.EFFECTIVE;
                break;
            case BUG:
                if (other == FIRE || other == FIGHTING || other == POISON || other == FLYING || other == GHOST)
                    return AttackEffectiveness.NOT_EFFECTIVE;
                if (other == GRASS || other == PSYCHIC)
                    return AttackEffectiveness.EFFECTIVE;
                break;
            case ROCK:
                if (other == FIGHTING || other == GROUND)
                    return AttackEffectiveness.NOT_EFFECTIVE;
                if (other == FIRE || other == ICE || other == FLYING || other == BUG)
                    return AttackEffectiveness.EFFECTIVE;
                break;
            case GHOST:
                if (other == NORMAL || other == PSYCHIC)
                    return AttackEffectiveness.MISS;
                if (other == GHOST)
                    return AttackEffectiveness.NOT_EFFECTIVE;
                if (other == PSYCHIC || other == GHOST)
                    return AttackEffectiveness.EFFECTIVE;
                break;
            case DRAGON:
                if (other == DRAGON)
                    return AttackEffectiveness.EFFECTIVE;
                break;
        }
        return AttackEffectiveness.NORMAL;
    }
}