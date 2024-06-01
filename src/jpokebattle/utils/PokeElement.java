package jpokebattle.utils;

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

    public AttackResult getAttackResult(PokeElement other)
    {
        if (this == other)
            return AttackResult.NOT_EFFECTIVE;

        switch (this)
        {
            case NORMAL:
                if (other == ROCK)
                    return AttackResult.NOT_EFFECTIVE;
                if (other == GHOST)
                    return AttackResult.MISS;
                break;
            case FIRE:
                if (other == FIRE || other == WATER || other == ROCK || other == DRAGON)
                    return AttackResult.NOT_EFFECTIVE;
                if (other == GRASS || other == ICE || other == BUG)
                    return AttackResult.EFFECTIVE;
                break;
            case WATER:
                if (other == WATER || other == GRASS || other == DRAGON)
                    return AttackResult.NOT_EFFECTIVE;
                if (other == FIRE || other == GROUND || other == ROCK)
                    return AttackResult.EFFECTIVE;
                break;
            case ELECTRIC:
                if (other == ELECTRIC || other == GRASS || other == DRAGON)
                    return AttackResult.NOT_EFFECTIVE;
                if (other == WATER || other == FLYING)
                    return AttackResult.EFFECTIVE;
                break;
            case GRASS:
                if (other == FIRE || other == GRASS || other == POISON || other == FLYING || other == BUG || other == DRAGON)
                    return AttackResult.NOT_EFFECTIVE;
                if (other == WATER || other == GROUND || other == ROCK)
                    return AttackResult.EFFECTIVE;
                break;
            case ICE:
                if (other == FIRE || other == WATER || other == ICE)
                    return AttackResult.NOT_EFFECTIVE;
                if (other == GRASS || other == GROUND || other == FLYING || other == DRAGON)
                    return AttackResult.EFFECTIVE;
                break;
            case FIGHTING:
                if (other == POISON || other == FLYING || other == PSYCHIC || other == BUG)
                    return AttackResult.NOT_EFFECTIVE;
                if (other == NORMAL || other == ICE || other == ROCK)
                    return AttackResult.EFFECTIVE;
                break;
            case POISON:
                if (other == POISON || other == GROUND || other == ROCK || other == GHOST)
                    return AttackResult.NOT_EFFECTIVE;
                if (other == GRASS)
                    return AttackResult.EFFECTIVE;
                break;
            case GROUND:
                if (other == GRASS || other == BUG)
                    return AttackResult.NOT_EFFECTIVE;
                if (other == FIRE || other == ELECTRIC || other == POISON || other == ROCK)
                    return AttackResult.EFFECTIVE;
                break;
            case FLYING:
                if (other == ELECTRIC || other == ROCK)
                    return AttackResult.NOT_EFFECTIVE;
                if (other == GRASS || other == FIGHTING || other == BUG)
                    return AttackResult.EFFECTIVE;
                break;
            case PSYCHIC:
                if (other == PSYCHIC)
                    return AttackResult.NOT_EFFECTIVE;
                if (other == FIGHTING || other == POISON)
                    return AttackResult.EFFECTIVE;
                break;
            case BUG:
                if (other == FIRE || other == FIGHTING || other == POISON || other == FLYING || other == GHOST)
                    return AttackResult.NOT_EFFECTIVE;
                if (other == GRASS || other == PSYCHIC)
                    return AttackResult.EFFECTIVE;
                break;
            case ROCK:
                if (other == FIGHTING || other == GROUND)
                    return AttackResult.NOT_EFFECTIVE;
                if (other == FIRE || other == ICE || other == FLYING || other == BUG)
                    return AttackResult.EFFECTIVE;
                break;
            case GHOST:
                if (other == NORMAL || other == PSYCHIC)
                    return AttackResult.MISS;
                if (other == GHOST)
                    return AttackResult.NOT_EFFECTIVE;
                if (other == PSYCHIC || other == GHOST)
                    return AttackResult.EFFECTIVE;
                break;
            case DRAGON:
                if (other == DRAGON)
                    return AttackResult.EFFECTIVE;
                break;
        }
        return AttackResult.NORMAL;
    }
}