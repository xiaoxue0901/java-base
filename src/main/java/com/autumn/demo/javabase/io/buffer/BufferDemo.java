package com.autumn.demo.javabase.io.buffer;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/18
 * @time 14:15
 * @description Buffer使用
 * 1.
 */
@Slf4j
public class BufferDemo {
    public static final String ABS_FILE = "";

    /**
     * 构造FileChannel对象
     *
     * @param path
     * @throws IOException
     */
    private static void constructFileChannel(Path path) throws IOException {
        //首先，从文件中获得一个通道（channel），通道是用于磁盘文件的一种抽象，它使我们可 以访问诸如内存映射、文件加锁机制以及文件间快速数据传递等操作系统特性。
        FileChannel channel = FileChannel.open(path);
        // FileInputStream
        FileChannel finChannel = new FileInputStream(ABS_FILE).getChannel();
        // FileOutputStream
        FileChannel fonChannel = new FileOutputStream(ABS_FILE).getChannel();
        // RandomAccessFile
        FileChannel rafChannel = new RandomAccessFile(ABS_FILE, "r").getChannel();
    }

    /**
     * Buffer使用
     *
     * @param path
     * @throws IOException
     */
    private static void useBufferReadAndWrite(Path path) throws IOException {
        FileChannel channel = FileChannel.open(path);
        //支持的模式有三种：
        //, FileChannel.MapMode.READ_ONLY;FileChannel.MapMode.READ_WRITE ：FileChannel.MapMode.PRIVATE
        // 映射到内存中
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 10);
        // 转为缓冲区
        ByteBuffer byteBuffer = mappedByteBuffer.asReadOnlyBuffer();
        // 方法使用
        // 返回给定位置的代码单元。除非对底层的代码单元感兴趣，否则不需要调用这个方法
        boolean remain = byteBuffer.hasRemaining();
        // 返回缓冲区的界限位置, 即没有任何值可用的第一个位置
        int limit = byteBuffer.limit();
        // 从当前位置获取一个字节, 并将当前位置移动到下一个字节
        byte b = byteBuffer.get();
        // 从指定索引处获取一个字节
        byte b1 = byteBuffer.get(1);
        // 向当前位置推入一个字节, 并将当前位置移动到下一个字节,
        byteBuffer.put((byte) 1);
        // 向指定索引处推入一个字节, 返回对这个缓冲区的应用.
        byteBuffer.put(3, (byte) 4);
        // 用缓冲区中的字节来填充字节数组, 或者字节数组的某个区域, 并将当前位置向前移动读入的字节数个位置.
        byte[] destination = new byte[1024];
        byteBuffer.get(destination);
        byteBuffer.get(destination, 3, 200);
        // 将字节数组中的所有字节或者给定区域的字节都推入缓冲区中，并将当前位置向前 移动写出的字节数个位置。
        byte[] source = new byte[2048];
        byteBuffer.put(source);
        byteBuffer.put(source, 5, 390);
        // getXXX(), putXXX(), 上面的方面是针对byte数据类型, 还有针对其他的基本数据类型的:比如
        byteBuffer.getInt();
        byteBuffer.getDouble();
        byteBuffer.getFloat();
        byteBuffer.getChar();
        byteBuffer.getShort();
        byteBuffer.getLong();

        // 构建具有给定容量的缓冲区
        ByteBuffer init = ByteBuffer.allocate(1024);
        byte[] source2 = new byte[2];
        // 苟江具有指定容量的缓冲区, 该缓冲区是对给定数组的包装.
        ByteBuffer init2 = ByteBuffer.wrap(source2);
        // 转为字符缓冲区
        CharBuffer charBuffer = byteBuffer.asCharBuffer();
        // 还有其他基本类型的缓冲区, 方法类型
        DoubleBuffer doubleBuffer = byteBuffer.asDoubleBuffer();
        LongBuffer longBuffer = byteBuffer.asLongBuffer();
        ShortBuffer shortBuffer = byteBuffer.asShortBuffer();
        FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
        IntBuffer intBuffer = byteBuffer.asIntBuffer();

    }

    private static void bufferApi(Buffer buffer) {
        // 使用buffer的方法
        // 通过将位置复位到0, 并将界限设置到容量, 使这个缓冲区为写出做好准备
        buffer.clear();
        // 将界限设置到位置, 并将位置复位到0, 为缓冲区读入做好准备
        buffer.flip();
        // 将读写位置复位到0, 保持界限不变, 使这个缓冲区为重新读入相同的值做好准备
        buffer.rewind();
        // 将这个缓冲区的标记设置到读写位置
        buffer.mark();
        // 将这个缓冲区的位置设置到标记, 从而允许被标记部分可以再次被读入或写出
        buffer.reset();
        // 返回剩余可读入或可写出的值的数量, 即界限与位置直间的差异
        int remain = buffer.remaining();
        // 返回缓冲区的位置
        int pos = buffer.position();
        // 返回缓冲区的容量
        int cap =buffer.capacity();
    }

    public static void useBuffer() {
        // 初始化一个容量为20的buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(28);
        String weather = "今天天气晴朗";
        // 写入buffer
        byteBuffer.put(weather.getBytes(StandardCharsets.UTF_8));
        log.info("write buffer:{}", byteBuffer);
        // 翻转到读模式: position=0; limit=weather.len
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            byte[] dest = new byte[18];
            log.info("相对索引不影响position, limit:{}", byteBuffer.get(1));
            byteBuffer.get(dest, byteBuffer.position(), byteBuffer.limit());
            log.info("read buffer{}", new String(dest, StandardCharsets.UTF_8));
            log.info("read after:{}", byteBuffer);
        }
        // clear: 将读模式切换为写模式,position置为0,limit=capacity.   写模式覆盖之前的数据来完成清除
        byteBuffer.clear();
        byte[] dest = new byte[18];
        byteBuffer.get(dest, 0, 18);
        log.info("clear data{}", new String(dest, StandardCharsets.UTF_8));
        byteBuffer.clear();
        log.info("clear 后的buffer:{}", byteBuffer);


        byteBuffer.put(weather.substring(0,4).getBytes(StandardCharsets.UTF_8));
        log.info("write 后的buffer:{}", byteBuffer);

    }

    public static void useBufferDuplicate() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(28);
        String weather = "今天天气晴朗";
        // 写入buffer
        byteBuffer.put(weather.getBytes(StandardCharsets.UTF_8));
        log.info("first buffer:{}", byteBuffer);

        ByteBuffer duplicate = byteBuffer.duplicate();
        log.info("duplicate buffer:{}", duplicate);
        // byteBuffer.flip();
        // log.info("byteBuffer flip:{}", byteBuffer);
        // log.info("duplicate not flip:{}", duplicate);
        // // byteBuffer读2个
        // byte[] dest1 = new byte[6];
        // byteBuffer.get(dest1, 0, 6);
        // log.info("dest1 get byteBuffer:{}", byteBuffer);
        // log.info("dest1 get duplicate:{}", duplicate);
        // // duplicate读4个
        // duplicate.flip();
        // log.info("duplicate  flip:{}", duplicate);
        // byte[] dest2 = new byte[12];
        // duplicate.get(dest2, 0, 12);
        // // 打印结果
        // log.info("get byteBuffer:{}, message:{}", byteBuffer, new String(byteBuffer.array()));
        // log.info("get duplicate:{}, message:{}", duplicate, new String(duplicate.array()));

        byteBuffer.put("雨".getBytes(StandardCharsets.UTF_8));
        log.info("put byteBuffer:{}, message:{}", byteBuffer, new String(byteBuffer.array()));
        duplicate.flip();
        byte[] dest3 = new byte[18];
        duplicate.get(dest3, 0, 18);
        log.info("not put byteBuffer:{} message:{}", duplicate, new String(dest3));

        duplicate.flip();
        duplicate.put("晴".getBytes(StandardCharsets.UTF_8));
        log.info("not put byteBuffer:{}, message:{}", byteBuffer, new String(byteBuffer.array()));
        log.info("put byteBuffer:{} message:{}", duplicate, new String(duplicate.array()));
    }

    public static void useBufferSlice() {
        // 1. buffer初始化,分配容量
        ByteBuffer byteBuffer = ByteBuffer.allocate(24);
        // 2. buffer写操作
        String weather = "今天下雨";
        byteBuffer.put(weather.getBytes(StandardCharsets.UTF_8));
        // 3. filp切换为读模式
        byteBuffer.flip();
        // 4. 读数据
        byte[] dest = new byte[6];
        byteBuffer.get(dest,0, 6);
        log.info("read dest:{}, read byteBuffer:{}", new String(dest, StandardCharsets.UTF_8), byteBuffer);
        // slice创建新缓冲区
        byteBuffer.clear();
        ByteBuffer slice = byteBuffer.slice();
        log.info("slice :{}, slice data:{} ", slice, new String(slice.array(), StandardCharsets.UTF_8));
        slice.put("适合睡觉".getBytes(StandardCharsets.UTF_8));
        log.info("slice put :{}, slice data:{} ", slice, new String(slice.array(), StandardCharsets.UTF_8));
        slice.flip();
        byte[] sliceData = new byte[12];
        slice.get(sliceData, 0, slice.remaining());
        log.info("slice read dest:{}, read byteBuffer:{}", new String(sliceData, StandardCharsets.UTF_8), slice);

    }



    public static void main(String[] args) {

        // useBufferDuplicate();
        // use slice
        useBufferSlice();
    }
}
