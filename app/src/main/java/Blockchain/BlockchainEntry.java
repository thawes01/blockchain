package Blockchain;

import Blockchain.core.Block;

/**
 * Represents an entry in a blockchain.
 *
 * This contains a block in the blockchain together with meta-data concerning
 * the block's creation.
 *
 * @param block  A {@link Block} for a blockchain
 * @param generationTime  the time, in milliseconds, that it took to generate
 *                        {@code block}
 */
public record BlockchainEntry(Block block, long generationTime) {
}
