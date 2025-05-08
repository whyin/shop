package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional //리드 온리라 트랜젝션 따로 설정
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    /*@Transactional
    public void updateItem(Long itemId, Book param) {
        //실제 DB에 있는 영속 상태 아이템을 찾아옴 (findItem은 영속)
        Item findItem = itemRepository.findOne(itemId);

        findItem.setPrice(param.getPrice());
        findItem.setName(param.getName());
        findItem.setStockQuantity(param.getStockQuantity());
    }*/

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        //실제 DB에 있는 영속 상태 아이템을 찾아옴 (findItem은 영속)
        Item findItem = itemRepository.findOne(itemId);

        /**
         * findItem.change(name, price, stockQuantity); << 만드는 게 굳
         */
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }

    /**
     * 업데이트 사항이 많으면 서비스 계층에 DTO하나를 만들어라
     */

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
